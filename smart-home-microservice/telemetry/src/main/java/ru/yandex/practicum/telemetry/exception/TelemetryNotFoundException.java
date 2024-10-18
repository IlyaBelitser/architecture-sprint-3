package ru.yandex.practicum.telemetry.exception;

import java.util.UUID;

public class TelemetryNotFoundException extends RuntimeException {
    public TelemetryNotFoundException(UUID deviceId) {
        super("Телеметрию устройства с ID " + deviceId + " не найдено.");
    }
}
