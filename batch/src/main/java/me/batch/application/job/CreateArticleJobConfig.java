package me.batch.application.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.batch.application.model.ArticleModel;
import me.batch.domain.entity.Article;
import me.batch.domain.repository.ArticleRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CreateArticleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ArticleRepository articleRepository;
    private final JdbcTemplate jdbcTemplate;

    @Bean
    public Job createArticleJob() {
        return jobBuilderFactory.get("createArticleJob")
                .incrementer(new RunIdIncrementer())
                .start(createArticleStep())
                .build();
    }

    @Bean
    public Step createArticleStep() {
        return stepBuilderFactory.get("createArticleStep")
                .<ArticleModel, Article>chunk(5)
                .reader(createArticleReader())
                .processor(createArticleProcessor())
                .writer(createArticleWriterWithJpa())
                .build();
//        return stepBuilderFactory.get("createArticleStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("STEP!!");
//                    return RepeatStatus.FINISHED;
//                }).build();
    }

    @Bean
    public FlatFileItemReader<ArticleModel> createArticleReader() {
        return new FlatFileItemReaderBuilder<ArticleModel>()
                .name("createArticleReader")
                .resource(new ClassPathResource("Articles.csv"))
                .delimited()
                .names("title", "content")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(ArticleModel.class)
                .build();
    }

    @Bean
    public ItemProcessor<ArticleModel, Article> createArticleProcessor() {
//        LocalDateTime now = LocalDateTime.now();

        return articleModel -> Article.builder()
                .title(articleModel.getTitle())
                .content(articleModel.getContent())
//                .createdAt(now) // JPA 사용할때는 자동으로 들어가므로 사용안해도됨.
                .build();
    }

    /**
     * JPA 를 이용한 Writer
     * @return
     */
    @Bean
    public RepositoryItemWriter<Article> createArticleWriterWithJpa() {
        return new RepositoryItemWriterBuilder<Article>()
                .repository(articleRepository)
                .build();
    }

    /**
     * JDBC 를 이용한 Writer (error 발생중..)
     * @return
     */
//    @Bean
//    public ItemWriter<Article> createArticleWriterWithJdbc() {
//        return articles -> jdbcTemplate.batchUpdate("insert into Article (title, content, createdAt) values (?, ?, ?)",
//                articles,
//                5,
//                (ps, article) -> {
//                    ps.setObject(1, article.getTitle());
//                    ps.setObject(2, article.getContent());
//                    ps.setObject(3, article.getCreatedAt());
//                });
//    }
}
