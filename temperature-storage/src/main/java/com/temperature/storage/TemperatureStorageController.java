package com.temperature.storage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Temperature Storage")
@RestController
@RequestMapping(Route.STORAGE)
public class TemperatureStorageController {

    private final TemperatureStorageService service;

    @Autowired
    public TemperatureStorageController(final TemperatureStorageService service) {
        this.service = service;
    }

    @Operation(description = "Fetch stored temperature readings.")
    @ApiResponse(responseCode = "200", description = "Successful list of stored temperature readings.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TemperatureStorage>> getReadings() {
        return ResponseEntity.ok(service.listReadings());
    }

}
