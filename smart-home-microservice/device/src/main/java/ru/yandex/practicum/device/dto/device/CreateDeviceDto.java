package ru.yandex.practicum.device.dto.device;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateDeviceDto {
    private Long deviceTypeId;
    private UUID moduleId;
    private UUID scenarioId;
    private String name;
    private String serialNumber;
}
