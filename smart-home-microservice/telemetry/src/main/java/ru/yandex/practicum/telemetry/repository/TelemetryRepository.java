package ru.yandex.practicum.telemetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.telemetry.entity.Telemetry;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, UUID> {
    Telemetry findTopByDeviceIdOrderByReceivedAtDesc(UUID deviceId);
}
