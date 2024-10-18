package ru.yandex.practicum.device.service;

import ru.yandex.practicum.device.dto.device.CreateDeviceDto;
import ru.yandex.practicum.device.dto.device.DeviceDto;
import ru.yandex.practicum.device.dto.device.UpdateDeviceDto;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    DeviceDto create(CreateDeviceDto deviceDto);
    DeviceDto get(UUID deviceId);
    List<DeviceDto> getAll();
    DeviceDto update(UUID deviceId, UpdateDeviceDto deviceDto);
    void delete(UUID deviceId);
    void setStatus(UUID deviceId, String status);
    void execute(UUID deviceId, String command);
}
