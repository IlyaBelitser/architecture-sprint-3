package ru.yandex.practicum.device.service;

import ru.yandex.practicum.device.dto.deviceType.CreateDeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.DeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.UpdateDeviceTypeDto;

import java.util.List;

public interface DeviceTypeService {
    DeviceTypeDto create(CreateDeviceTypeDto createDeviceTypeDto);
    DeviceTypeDto get(Long deviceTypeId);
    List<DeviceTypeDto> getAll();
    DeviceTypeDto update(Long deviceTypeId, UpdateDeviceTypeDto updateDeviceTypeDto);
    void delete(Long deviceTypeId);
}
