package com.temperature.healthcheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HealthCheck")
@RestController
@RequestMapping(Route.HEALTHCHECK)
public class HealthCheckController {

    private static final String HEALTHCHECK_MESSAGE = "Service temperature-reader is up.";

    @Operation(description = "Performs a health-check of the service.")
    @ApiResponse(responseCode = "200", description = "Successful health-check.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthCheckResponse> performHealthCheck() {
        return ResponseEntity.ok(
                new HealthCheckResponse(HEALTHCHECK_MESSAGE)
        );
    }

}
