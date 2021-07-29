package me.aop.v1;

import me.aop.v1.Store;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StoreTest {

    @Test
    void test() {
        // Given
        Store store = new Store();

        // When & Then
        assertThat(store.getOperationTime()).isEqualTo("AM 08:00 ~ PM 08:00");
    }
}
