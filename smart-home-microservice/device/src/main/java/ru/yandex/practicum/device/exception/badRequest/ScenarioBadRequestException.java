package ru.yandex.practicum.device.exception.badRequest;

import java.util.UUID;

public class ScenarioBadRequestException extends RuntimeException {
    public ScenarioBadRequestException(UUID scenarioId) {
        super("Сценарий использования устройства с ID " + scenarioId + " не найдено.");
    }
}
