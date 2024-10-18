package ru.yandex.practicum.device.exception.notFound;

import java.util.UUID;

public class ScenarioNotFoundException extends RuntimeException {
    public ScenarioNotFoundException(UUID scenarioId) {
        super("Сценарий использования устройства с ID " + scenarioId + " не найдено.");
    }
}
