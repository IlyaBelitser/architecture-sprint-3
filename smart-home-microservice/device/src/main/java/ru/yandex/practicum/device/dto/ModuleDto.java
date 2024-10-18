package ru.yandex.practicum.device.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ModuleDto {
    private UUID id;
    private UUID moduleTypeId;
    private UUID houseId;
    private Long deviceTypeId;
    private String name;
    private String serialNumber;
}
