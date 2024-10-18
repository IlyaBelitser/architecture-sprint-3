package ru.yandex.practicum.device.exception.badRequest;

import java.util.UUID;

public class DeviceBadRequestException extends RuntimeException {
    public DeviceBadRequestException(UUID deviceId) {
        super("Устройство с ID " + deviceId + " не найдено.");
    }
}
