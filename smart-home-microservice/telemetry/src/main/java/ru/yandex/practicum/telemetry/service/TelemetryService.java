package ru.yandex.practicum.telemetry.service;

import ru.yandex.practicum.telemetry.dto.TelemetryDto;
import ru.yandex.practicum.telemetry.dto.CreateTelemetryDto;

import java.util.UUID;

public interface TelemetryService {
    TelemetryDto create(UUID id, CreateTelemetryDto telemetryDto);
    TelemetryDto getLatest(UUID id);
}
