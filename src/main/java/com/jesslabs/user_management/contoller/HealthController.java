package com.jesslabs.user_management.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Health Controller", description = "APIs for health monitoring of the application.")
public class HealthController {

    @Operation(
            summary = "Health Check",
            description = "Endpoint to verify if the application is running and healthy."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application is healthy."),
            @ApiResponse(responseCode = "500", description = "Application is not healthy.")
    })
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("User Management Service is healthy");
    }
}