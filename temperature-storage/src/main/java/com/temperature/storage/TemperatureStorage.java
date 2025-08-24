package com.temperature.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "temperature_storage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperatureStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "original_reading")
    private Double originalReading;

    @Column(name = "temperature_in_celsius")
    private Double celsius;

    @Column(name = "temperature_in_fahrenheit")
    private Double fahrenheit;

    @Column(name = "temperature_in_kelvin")
    private Double kelvin;

    @Column(name = "reading_date")
    private Instant readingDate;


}
