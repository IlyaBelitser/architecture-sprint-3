package ru.yandex.practicum.telemetry.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import ru.yandex.practicum.telemetry.dto.DeviceDto;
import ru.yandex.practicum.telemetry.exception.DeviceBadRequestException;
import org.springframework.web.client.RestTemplate;
import ru.yandex.practicum.telemetry.dto.CreateTelemetryDto;
import ru.yandex.practicum.telemetry.dto.TelemetryDto;
import ru.yandex.practicum.telemetry.entity.Telemetry;
import ru.yandex.practicum.telemetry.exception.TelemetryNotFoundException;
import ru.yandex.practicum.telemetry.repository.TelemetryRepository;

@Service
@RequiredArgsConstructor
public class TelemetryServiceImpl implements TelemetryService {
    private static final Logger log = LoggerFactory.getLogger(TelemetryServiceImpl.class);
    private final TelemetryRepository telemetryRepository;
    private final RestTemplate restTemplate;
    private final String deviceServiceUrl = "http://device:8080/api/devices/{id}";

    @Override
    public TelemetryDto create(UUID deviceId, CreateTelemetryDto telemetryDto) {
        Telemetry telemetry = new Telemetry();
        telemetry.setDeviceId(deviceId); // проверка deviceId не сделана для уменьшения нагрузки на Device Service
        telemetry.setData(telemetryDto.getData());
        telemetry.setReceivedAt(telemetryDto.getReceivedAt());
        telemetry.setIpAndPort(telemetryDto.getIpAndPort());
        telemetryRepository.save(telemetry);
        return convertToDto(telemetry);
    }

    @Override
    public TelemetryDto getLatest(UUID deviceId) {
        checkExistDeviceId(deviceId);
        Telemetry telemetry = telemetryRepository.findTopByDeviceIdOrderByReceivedAtDesc(deviceId);
        if(telemetry == null)
        {
            throw new TelemetryNotFoundException(deviceId);
        }
        return convertToDto(telemetry);
    }

    private void checkExistDeviceId(UUID deviceId) {
        String url = deviceServiceUrl.replace("{id}", deviceId.toString());

        try {
            DeviceDto deviceDto = restTemplate.getForObject(url, DeviceDto.class);

            if (deviceDto == null || !Objects.equals(deviceDto.getId(), deviceId)) {
                throw new DeviceBadRequestException(deviceId);
            }
            log.info("Получена информация об устройстве {}", deviceId);
        } catch (Exception e) {
            log.warn("Ошибка в получении информации об устройстве с ID {} {}", deviceId, e.getMessage());
            throw new DeviceBadRequestException(deviceId);
        }
    }

    private TelemetryDto convertToDto(Telemetry telemetry) {
        TelemetryDto dto = new TelemetryDto();
        dto.setId(telemetry.getId());
        dto.setData(telemetry.getData());
        dto.setDeviceId(telemetry.getDeviceId());
        dto.setReceivedAt(telemetry.getReceivedAt());
        dto.setIpAndPort(telemetry.getIpAndPort());
        return dto;
    }
}
