package ru.yandex.practicum.device.exception.notFound;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(String command) {
        super("Команда [ " + command + " ] не найдена.");
    }
}
