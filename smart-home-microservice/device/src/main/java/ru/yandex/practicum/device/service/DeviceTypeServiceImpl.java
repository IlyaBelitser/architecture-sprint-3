package ru.yandex.practicum.device.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.device.dto.deviceType.CreateDeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.DeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.UpdateDeviceTypeDto;
import ru.yandex.practicum.device.entity.DeviceType;
import ru.yandex.practicum.device.exception.notFound.DeviceTypeNotFoundException;
import ru.yandex.practicum.device.repository.DeviceTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final DeviceTypeRepository deviceTypeRepository;

    @Override
    public DeviceTypeDto create(CreateDeviceTypeDto createDeviceTypeDto) {
        DeviceType deviceType = new DeviceType();
        deviceType.setName(createDeviceTypeDto.getName());
        deviceType.setDescription(createDeviceTypeDto.getDescription());
        deviceTypeRepository.save(deviceType);
        return convertToDto(deviceType);
    }

    @Override
    public DeviceTypeDto get(Long deviceTypeId) {
        DeviceType deviceType = deviceTypeRepository.findById(deviceTypeId)
            .orElseThrow(() -> new DeviceTypeNotFoundException(deviceTypeId));
        return convertToDto(deviceType);
    }

    @Override
    public List<DeviceTypeDto> getAll() {
        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        List<DeviceTypeDto> deviceTypeDtos = new ArrayList<>(deviceTypes.size());
        for (DeviceType deviceType : deviceTypes)
        {
            deviceTypeDtos.add(convertToDto(deviceType));
        }
        return deviceTypeDtos;
    }

    @Transactional
    @Override
    public DeviceTypeDto update(Long deviceTypeId, UpdateDeviceTypeDto updateDeviceTypeDto) {
        DeviceType deviceType = new DeviceType();
        deviceType.setName(updateDeviceTypeDto.getName());
        deviceType.setDescription(updateDeviceTypeDto.getDescription());
        deviceTypeRepository.save(deviceType);
        return convertToDto(deviceType);
    }

    public void delete(Long deviceTypeId) {
        deviceTypeRepository.findById(deviceTypeId)
                .orElseThrow(() -> new DeviceTypeNotFoundException(deviceTypeId));
        deviceTypeRepository.deleteById(deviceTypeId);
    }

    private DeviceTypeDto convertToDto(DeviceType deviceType) {
        DeviceTypeDto dto = new DeviceTypeDto();
        dto.setId(deviceType.getId());
        dto.setName(deviceType.getName());
        dto.setDescription(deviceType.getDescription());
        return dto;
    }
}
