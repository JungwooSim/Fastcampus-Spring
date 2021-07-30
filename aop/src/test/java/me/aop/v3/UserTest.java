package me.aop.v3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTest {
    @Autowired
    private Store store;

    @Autowired
    private Library library;

    @Test
    void test() {
        // Given
        User user = new User();

        // When & Then
        assertThat(user.greeting()).isEqualTo("hello");
    }

    @Test
    void testVisitToStore() {
        // Given
        User user = new User();
        user.setName("홍길동");

        store.setVisitCountByUser(11);

        // When
        user.visitTo(store);

    }

    @Test
    public void testVisitToLibrary() {
        // Given
        User user = new User();
        user.setName("AOP");

        library.setName("행복 도서관");
        library.setVisitCountByUser(11);

        // When
        user.visitTo(library);
    }
}
