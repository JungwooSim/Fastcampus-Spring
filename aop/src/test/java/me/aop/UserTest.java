package me.aop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Test
    public void test() {
        // Given
        User user = new User();

        // When & Then
        assertThat(user.greeting()).isEqualTo("hello");
    }
}
