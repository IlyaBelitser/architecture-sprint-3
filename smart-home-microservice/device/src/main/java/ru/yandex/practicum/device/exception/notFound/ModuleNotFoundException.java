package ru.yandex.practicum.device.exception.notFound;

import java.util.UUID;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException(UUID moduleId) {
        super("Модуль с ID " + moduleId + " не найдено.");
    }
}
