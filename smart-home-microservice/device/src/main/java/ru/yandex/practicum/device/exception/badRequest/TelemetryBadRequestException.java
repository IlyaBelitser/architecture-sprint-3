package ru.yandex.practicum.device.exception.badRequest;

import java.util.UUID;

public class TelemetryBadRequestException extends RuntimeException {
    public TelemetryBadRequestException(UUID deviceId) {
        super("Ошибка при получении ip адреса из телеметрии устройства с ID " + deviceId);
    }
}
