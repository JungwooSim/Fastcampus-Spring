package me.aop.v2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserTest {
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

        Store store = new Store();

        // When
        user.visitTo(store);
    }
}
