package com.temperature.storage;

import lombok.Data;

import java.time.Instant;

@Data
public class TemperatureReading {

    private Double temperature;
    private TemperatureFormat format;
    private Instant moment = Instant.now();

    public Double toCelsius() {
        return format.toCelsius(temperature);
    }

    public Double toFahrenheit() {
        return format.toFahrenheit(temperature);
    }

    public Double toKelvin() {
        return format.toKelvin(temperature);
    }

}
