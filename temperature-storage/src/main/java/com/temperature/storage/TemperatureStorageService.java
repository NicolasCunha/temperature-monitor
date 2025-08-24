package com.temperature.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureStorageService {

    private final TemperatureStorageRepository repository;

    @Autowired
    public TemperatureStorageService(final TemperatureStorageRepository repository) {
        this.repository = repository;
    }

    public List<TemperatureStorage> listReadings() {
        return repository.findAll();
    }

    public void createReading(final TemperatureReading temperatureReading) {
        final TemperatureStorage temperatureStorage = TemperatureStorage.builder()
                .originalReading(temperatureReading.getTemperature())
                .celsius(temperatureReading.toCelsius())
                .fahrenheit(temperatureReading.toFahrenheit())
                .kelvin(temperatureReading.toKelvin())
                .readingDate(temperatureReading.getMoment())
                .build();
        this.repository.save(temperatureStorage);
    }

}
