package ru.yandex.practicum.telemetry.exception;

import java.util.UUID;

public class DeviceBadRequestException extends RuntimeException {
    public DeviceBadRequestException(UUID deviceId) {
        super("Устройство с ID " + deviceId + " не найдено.");
    }
}
