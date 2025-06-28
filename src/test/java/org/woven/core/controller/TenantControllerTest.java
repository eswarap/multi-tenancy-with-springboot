package org.woven.core.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.woven.core.service.TenantService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TenantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TenantService tenantService;

    @Test
    void testCreateTenant() throws Exception {
        doNothing().when(tenantService).createTenant("testTenant");
        mockMvc.perform(post("/tenants/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"testTenant\""))
                .andExpect(status().isOk())
                .andExpect(content().string("Tenant created successfully"));
    }

    @Test
    void testListTenants() throws Exception {
        mockMvc.perform(get("/tenants/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("List of tenants"));
    }

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/tenants/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tenant service is healthy"));
    }

    @Test
    void testTenantInfo() throws Exception {
        mockMvc.perform(get("/tenants/info"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tenant information"));
    }

    @Test
    void testTenantStatus() throws Exception {
        mockMvc.perform(get("/tenants/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tenant status is active"));
    }

    @Test
    void testTenantDetails() throws Exception {
        mockMvc.perform(get("/tenants/{tenantId}/details", "123")
                        .param("tenantId", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Details for tenant: 123"));
    }

    @Test
    void testTenantDetails_Success() throws Exception {
        mockMvc.perform(get("/tenants/123/details"))
                .andExpect(status().isOk())
                .andExpect(content().string("Details for tenant: 123"));
    }

    @Test
    void testTenantDetails_BadRequest() throws Exception {
        mockMvc.perform(get("/tenants//details"))
                .andExpect(status().isNotFound());
    }
}
