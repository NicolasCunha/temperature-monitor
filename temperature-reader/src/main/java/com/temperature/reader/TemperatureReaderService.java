package com.temperature.reader;

import com.temperature.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TemperatureReaderService {

    private static final String TEMPERATURE_READING_TOPIC = "temperature.reading";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TemperatureReaderService(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public TemperatureReadingValidation validateReading(final TemperatureReading temperatureReading) {
        if (temperatureReading.getFormat() == null) {
            return new TemperatureReadingValidation(false, "Temperature reading must have a format: CELSIUS, FAHRENHEIT or KELVIN.");
        }

        if (temperatureReading.getTemperature() == null) {
            return new TemperatureReadingValidation(false, "Temperature reading must have a non-null reading.");
        }

        return new TemperatureReadingValidation(true, null);
    }

    public void createReading(final TemperatureReading temperatureReading) {
        final String jsonMessage = JsonUtil.toJson(temperatureReading);
        this.kafkaTemplate.send(TEMPERATURE_READING_TOPIC, jsonMessage);
    }

}
