package me.batch.application.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Date;
import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    private final JobExplorer jobExplorer;
    private final JobRepository jobRepository;

    /**
     * 해당 코드는 BATCH_JOB_EXECUTION 에서 STOPPED 된 상태를 확인 후 재실행
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Stop running jobs.");

        for (String jobName : jobExplorer.getJobNames()) {
            Set<JobExecution> runningJobExecutions = jobExplorer.findRunningJobExecutions(jobName);

            for (JobExecution jobExecution : runningJobExecutions) {
                jobExecution.setStatus(BatchStatus.STOPPED);
                jobExecution.setEndTime(new Date());
                for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
                    if (stepExecution.getStatus().isRunning()) {
                        stepExecution.setStatus(BatchStatus.STOPPED);
                        stepExecution.setEndTime(new Date());
                        jobRepository.update(stepExecution);
                    }
                }
                jobRepository.update(jobExecution);
                log.info("Updated job execution jobId : {}", jobExecution.getJobId());
            }
        }
        log.info("Stopped running jobs.");
    }
}
