package org.woven.core.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestingWebApplicationTests {

    @Autowired
    private TenantController controller;

    @Test
    void contextLoads() {
    }

    @Test
    void testHomePage() {
        ResponseEntity<String> response = controller.listTenants();
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
