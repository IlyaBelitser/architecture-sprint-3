package ru.yandex.practicum.device.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.device.dto.ModuleDto;
import ru.yandex.practicum.device.dto.ScenarioDto;
import ru.yandex.practicum.device.dto.TelemetryDto;
import ru.yandex.practicum.device.dto.device.UpdateDeviceDto;
import ru.yandex.practicum.device.entity.Device;
import ru.yandex.practicum.device.dto.device.DeviceDto;
import ru.yandex.practicum.device.entity.DeviceType;
import ru.yandex.practicum.device.exception.badRequest.DeviceTypeBadRequestException;
import ru.yandex.practicum.device.exception.badRequest.ModuleBadRequestException;
import ru.yandex.practicum.device.exception.badRequest.ScenarioBadRequestException;
import ru.yandex.practicum.device.exception.badRequest.TelemetryBadRequestException;
import ru.yandex.practicum.device.exception.notFound.CommandNotFoundException;
import ru.yandex.practicum.device.repository.DeviceRepository;
import ru.yandex.practicum.device.exception.notFound.DeviceNotFoundException;
import ru.yandex.practicum.device.repository.DeviceTypeRepository;
import org.springframework.web.client.RestTemplate;
import ru.yandex.practicum.device.dto.device.CreateDeviceDto;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private static final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final RestTemplate restTemplate;
    private final String telemetryServiceUrl = "http://telemetry:8080/api/devices/{id}/telemetry/latest";
    private final String houseServiceUrl = "http://house:8080/api/modules/{id}";
    private final String scenarioServiceUrl = "http://scenario:8080/api/scenarios/{id}";

    @Override
    public DeviceDto create(CreateDeviceDto deviceDto) {
        Device device = new Device();
        DeviceType type = getDeviceType(deviceDto.getDeviceTypeId());
        device.setType(type);

//        checkExistModuleId(device.getModuleId());
        device.setModuleId(device.getModuleId());

//        checkExistScenarioId(device.getScenarioId());
        device.setScenarioId(device.getScenarioId());

        device.setName(deviceDto.getName());
        device.setSerialNumber(deviceDto.getSerialNumber());
        deviceRepository.save(device);
        return convertToDto(device);
    }

    private void checkExistModuleId(UUID moduleId) {
        String url = houseServiceUrl.replace("{id}", moduleId.toString());

        try {
            ModuleDto moduleDto = restTemplate.getForObject(url,ModuleDto.class);

            if (moduleDto == null || !Objects.equals(moduleDto.getId(), moduleId)) {
                throw new Exception();
            }
            if (moduleDto == null) {
                throw new Exception("Сервис модулей прислал пустые данные");
            }

            if (!Objects.equals(moduleDto.getId(), moduleId)) {
                throw new Exception("ID модуля текущего запроса " + moduleId +
                        " и ID модуля ответа " + moduleDto.getId() + " не равны");
            }
            log.info("Получена информация о модуле с ID {}", moduleId);
        } catch (Exception e) {
            log.warn("Ошибка в получении информации о модуле с ID {} {}", moduleId, e.getMessage());
            throw new ModuleBadRequestException(moduleId);
        }
    }

    private void checkExistScenarioId(UUID scenarioId) {
        String url = scenarioServiceUrl.replace("{id}", scenarioId.toString());

        try {
            ScenarioDto scenarioDto = restTemplate.getForObject(url, ScenarioDto.class);

            if (scenarioDto == null) {
                throw new Exception("Сервис сценариев использования устройства прислал пустые данные");
            }

            if (!Objects.equals(scenarioDto.getId(), scenarioId)) {
                throw new Exception("ID сценария использования устройства текущего запроса " + scenarioId +
                        " и ID сценария использования устройства ответа " + scenarioDto.getId() + " не равны");
            }
            log.info("Получена информация о сценарии использования устройства с ID {}", scenarioId);
        } catch (Exception e) {
            log.warn("Ошибка в получении информации о сценарии использования устройства с ID {} {}", scenarioId, e.getMessage());
            throw new ScenarioBadRequestException(scenarioId);
        }
    }

    @Override
    public DeviceDto get(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId)
            .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        return convertToDto(device);
    }

    @Override
    public List<DeviceDto> getAll() {
        List<Device> devices = deviceRepository.findAll();
        List<DeviceDto> deviceDtos = new ArrayList<>(devices.size());
        for (Device device : devices)
        {
            deviceDtos.add(convertToDto(device));
        }
        return deviceDtos;
    }

    @Transactional
    @Override
    public DeviceDto update(UUID deviceId, UpdateDeviceDto deviceDto) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        DeviceType deviceType = getDeviceType(deviceDto.getDeviceTypeId());
        device.setType(deviceType);

        checkExistModuleId(device.getModuleId());
        device.setModuleId(device.getModuleId());

        checkExistScenarioId(device.getScenarioId());
        device.setScenarioId(device.getScenarioId());

        device.setName(deviceDto.getName());
        device.setSerialNumber(deviceDto.getSerialNumber());
        device.setCurrentValue(deviceDto.getCurrentValue());
        device.setTargetValue(deviceDto.getTargetValue());
        return convertToDto(device);
    }

    public void delete(UUID deviceId) {
        deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        deviceRepository.deleteById(deviceId);
    }

    private DeviceType getDeviceType(Long deviceTypeId) {
        return deviceTypeRepository.findById(deviceTypeId)
                .orElseThrow(() -> new DeviceTypeBadRequestException(deviceTypeId));
    }

    @Override
    public void setStatus(UUID deviceId, String status) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        device.setStatus(status);
        deviceRepository.save(device);
    }

    @Override
    public void execute(UUID deviceId, String command) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        String commandCore = command.split(" ")[0];
        String ipAndPort = getIpAndPort(deviceId);
        switch (commandCore) {
            case "set_temperature" -> {
                log.info("Отправили команду [ {} ] на устройство c ID {} ( {} )", command, deviceId, ipAndPort);
            }
            default -> throw new CommandNotFoundException(commandCore);
        }
    }

    private String getIpAndPort(UUID deviceId) {
        String url = telemetryServiceUrl.replace("{id}", deviceId.toString());

        try {
            TelemetryDto telemetryDto = restTemplate.getForObject(url, TelemetryDto.class);

            if (telemetryDto == null) {
                throw new Exception("Сервис телеметрии прислал пустые данные");
            }

            if (!Objects.equals(telemetryDto.getDeviceId(), deviceId)) {
                throw new Exception("ID устройства текущего запроса " + deviceId +
                        " и ID устройства ответа " + telemetryDto.getDeviceId() + " не равны");
            }
            log.info("Получен ip адрес из телеметрии устройства, устройство с ID {}", deviceId);
            return telemetryDto.getIpAndPort();
        } catch (Exception e) {
            log.warn("Ошибка при получении ip адреса из телеметрии устройства, устройство с ID {} {}", deviceId, e.getMessage());
            throw new TelemetryBadRequestException(deviceId);
        }
    }

    private DeviceDto convertToDto(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setDeviceTypeId(device.getType().getId());
        dto.setModuleId(device.getModuleId());
        dto.setScenarioId(device.getScenarioId());
        dto.setName(device.getName());
        dto.setSerialNumber(device.getSerialNumber());
        dto.setCurrentValue(device.getCurrentValue());
        dto.setTargetValue(device.getTargetValue());
        dto.setStatus(device.getStatus());
        return dto;
    }
}
