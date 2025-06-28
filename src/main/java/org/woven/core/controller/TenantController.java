package org.woven.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.woven.core.service.TenantService;

import javax.inject.Inject;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    // This controller can be used to handle tenant-related requests.

    private final TenantService tenantService;

    @Inject
    public TenantController(final TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTenant(@RequestBody String tenantName) {
        tenantService.createTenant(tenantName);
        return ResponseEntity.ok("Tenant created successfully");
    }

    // Example method to list tenants
    @GetMapping("/all")
    public ResponseEntity<String> listTenants() {
        // This method would typically return a list of tenants.
        // For simplicity, we return a placeholder response.
        //tenantService.list();
        return ResponseEntity.ok("List of tenants");
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        // This method can be used to check the health of the tenant service.
        return ResponseEntity.ok("Tenant service is healthy");
    }

    @GetMapping("/info")
    public ResponseEntity<String> tenantInfo() {
        // This method can be used to get information about the current tenant.
        // For simplicity, we return a placeholder response.
        return ResponseEntity.ok("Tenant information");
    }

    @GetMapping("/status")
    public ResponseEntity<String> tenantStatus() {
        // This method can be used to get the status of the current tenant.
        // For simplicity, we return a placeholder response.
        return ResponseEntity.ok("Tenant status is active");
    }

    @GetMapping("/{tenantId}/details")
    public ResponseEntity<String> tenantDetails(@RequestParam String tenantId) {
        // This method can be used to get details of a specific tenant.
        // For simplicity, we return a placeholder response.
        return ResponseEntity.ok("Details for tenant: " + tenantId);
    }

}
