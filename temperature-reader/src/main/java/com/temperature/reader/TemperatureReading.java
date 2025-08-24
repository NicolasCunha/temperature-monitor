package com.temperature.reader;

import lombok.Data;
import java.time.Instant;

@Data
public class TemperatureReading {

    private Double temperature;
    private TemperatureFormat format;
    private Instant moment = Instant.now();

}
