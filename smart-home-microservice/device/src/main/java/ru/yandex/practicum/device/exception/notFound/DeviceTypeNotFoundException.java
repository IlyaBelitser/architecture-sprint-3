package ru.yandex.practicum.device.exception.notFound;

public class DeviceTypeNotFoundException extends RuntimeException {
    public DeviceTypeNotFoundException(Long deviceTypeId) {
        super("Тип устройства с ID " + deviceTypeId + " не найдено.");
    }
}
