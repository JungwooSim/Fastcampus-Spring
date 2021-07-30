package me.aop.v3;

import me.aop.v3.Store;
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

    @Test
    void isVipUser() {
        // Given
        Store store = new Store();
        store.setVisitCountByUser(11);

        User user = new User();

        // When
        boolean result = store.isVIP(user);

        // Then
        assertThat(result).isTrue();
    }
}
