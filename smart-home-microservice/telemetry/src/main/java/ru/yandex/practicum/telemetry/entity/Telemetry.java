package ru.yandex.practicum.telemetry.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "telemetry")
@Data
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID deviceId;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    @Column(nullable = false)
    private String ipAndPort;
}
