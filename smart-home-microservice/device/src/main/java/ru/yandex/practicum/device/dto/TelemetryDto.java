package ru.yandex.practicum.device.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TelemetryDto {
    private UUID id;
    private UUID deviceId;
    private String data;
    private LocalDateTime receivedAt;
    private String ipAndPort;
}
