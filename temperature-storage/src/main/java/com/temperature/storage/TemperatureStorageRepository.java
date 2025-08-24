package com.temperature.storage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemperatureStorageRepository extends JpaRepository<TemperatureStorage, UUID> {
}
