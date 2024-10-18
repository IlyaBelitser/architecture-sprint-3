package ru.yandex.practicum.device.exception.badRequest;

import java.util.UUID;

public class ModuleBadRequestException extends RuntimeException {
    public ModuleBadRequestException(UUID moduleId) {
        super("Модуль с ID " + moduleId + " не найдено.");
    }
}
