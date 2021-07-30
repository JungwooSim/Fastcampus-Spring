package me.aop.v3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryTest {

    @Autowired
    Library library;

    @Test
    void test() {
        // Given
        Library library = new Library();
        library.setName("행복 도서관");

        // When
        String name = library.getName();

        // Then
        assertThat(name).isNotNull();
        assertThat(name).isEqualTo("행복 도서관");
    }

    @Test
    void visitedTo() {
        // Given
        library.setName("행복 도서관");

        User user = new User();
        user.setName("스프링");

        // When
        library.visitedBy(user);

        // Then
    }
}
