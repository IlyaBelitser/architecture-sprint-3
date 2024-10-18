package ru.yandex.practicum.device.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ScenarioDto {
    private UUID id;
    private UUID userId;
    private String Name;
    private String Description;
    private String Script;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
}
