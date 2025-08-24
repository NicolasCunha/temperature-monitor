package com.temperature.reader;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Temperature Reader")
@RestController
@RequestMapping(Route.READER)
public class TemperatureReaderController {

    private final TemperatureReaderService service;

    @Autowired
    public TemperatureReaderController(final TemperatureReaderService service) {
        this.service = service;
    }

    @Operation(description = "Creates a temperature reading.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reading has been created successfully."),
            @ApiResponse(responseCode = "400", description = "The temperature reading is invalid.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TemperatureReadingResponse> createReading(@RequestBody TemperatureReading temperatureReading) {
        final TemperatureReadingValidation validationResult = service.validateReading(temperatureReading);
        if (!validationResult.valid()) {
            return ResponseEntity.badRequest()
                    .body(new TemperatureReadingResponse(validationResult.message()));
        }
        this.service.createReading(temperatureReading);
        return ResponseEntity.ok(new TemperatureReadingResponse("Temperature reading created successfully."));
    }

}
