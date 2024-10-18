package ru.yandex.practicum.device.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_type_id")
    private DeviceType type;

    @Column(nullable = true)
    private UUID moduleId;

    @Column(nullable = true)
    private UUID scenarioId;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String serialNumber;

    @Column(nullable = true)
    private Double currentValue;

    @Column(nullable = true)
    private Double targetValue;

    @Column(nullable = true)
    private String status;
}
