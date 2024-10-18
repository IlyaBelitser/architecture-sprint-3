package ru.yandex.practicum.telemetry.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTelemetryDto {
    private String data;
    private LocalDateTime receivedAt;
    private String ipAndPort;
}
