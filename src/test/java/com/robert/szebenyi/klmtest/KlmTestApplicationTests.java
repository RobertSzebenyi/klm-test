package com.robert.szebenyi.klmtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class KlmTestApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
