package ru.yandex.practicum.device.exception.badRequest;

public class DeviceTypeBadRequestException extends RuntimeException {
    public DeviceTypeBadRequestException(Long deviceTypeId) {
        super("Тип устройства с ID " + deviceTypeId + " не найдено.");
    }
}
