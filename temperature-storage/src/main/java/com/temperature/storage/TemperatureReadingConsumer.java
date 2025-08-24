package com.temperature.storage;

import com.temperature.util.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureReadingConsumer {

    private static final String TEMPERATURE_READING_TOPIC = "temperature.reading";
    private final TemperatureStorageService service;

    @Autowired
    public TemperatureReadingConsumer(final TemperatureStorageService service) {
        this.service = service;
    }

    @KafkaListener(topics = TEMPERATURE_READING_TOPIC)
    public void listen(final ConsumerRecord<String, String> consumerRecord) {
        final String json = consumerRecord.value();
        final TemperatureReading temperatureReading = JsonUtil.fromJson(json, TemperatureReading.class);
        this.service.createReading(temperatureReading);
    }

}
