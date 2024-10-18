package ru.yandex.practicum.telemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.telemetry.dto.CreateTelemetryDto;
import ru.yandex.practicum.telemetry.dto.TelemetryDto;
import ru.yandex.practicum.telemetry.service.TelemetryService;

import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class TelemetryController {
    private final TelemetryService telemetryService;
    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);

    /**
     * Создание новой записи телеметрии устройства.
     *
     * @param createTelemetryDto данные телеметрии устройства для сохранения.
     * @return сохранённая запись об телеметрии устройства.
     */
    @PostMapping("/{id}/telemetry")
    public ResponseEntity<TelemetryDto> create(@PathVariable("id") UUID id, @RequestBody CreateTelemetryDto createTelemetryDto) {
        log.info("Создание новой записи телеметрии устройства");
        if (createTelemetryDto.getIpAndPort().isBlank()) {
            // получаем IP адрес и порт из X-Real-IP или X-Forwarded-For
        }
        return ResponseEntity.ok(telemetryService.create(id, createTelemetryDto));
    }

    /**
     * Получение последних данных телеметрии устройства.
     *
     * @param id идентификатор устройства.
     * @return информация последних данных телеметрии устройства.
     */
    @GetMapping("/{id}/telemetry/latest")
    public ResponseEntity<TelemetryDto> getLatest(@PathVariable("id") UUID id) {
        log.info("Получение последних данных телеметрии устройства, устройство с ID {}", id);
        return ResponseEntity.ok(telemetryService.getLatest(id));
    }
}
