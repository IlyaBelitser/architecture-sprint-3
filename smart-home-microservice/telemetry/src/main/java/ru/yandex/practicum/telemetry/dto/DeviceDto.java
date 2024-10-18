package ru.yandex.practicum.telemetry.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DeviceDto {
    private UUID id;
    private Long deviceTypeId;
    private UUID moduleId;
    private UUID scenarioId;
    private String name;
    private String serialNumber;
    private Double currentValue;
    private Double targetValue;
    private String status;
}
