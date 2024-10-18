package ru.yandex.practicum.telemetry.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class TelemetryDto {
    private UUID id;
    private UUID deviceId;
    private String data;
    private LocalDateTime receivedAt;
    private String ipAndPort;
}
