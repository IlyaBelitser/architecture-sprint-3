package ru.yandex.practicum.device.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.device.dto.device.CreateDeviceDto;
import ru.yandex.practicum.device.dto.device.DeviceDto;
import ru.yandex.practicum.device.dto.device.UpdateDeviceDto;
import ru.yandex.practicum.device.service.DeviceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

    /**
     * Создание новой записи устройства.
     *
     * @param createDeviceDto данные для сохранения.
     * @return сохранённая запись об устройстве.
     */
    @PostMapping("/")
    public ResponseEntity<DeviceDto> create(@RequestBody CreateDeviceDto createDeviceDto) {
        log.info("Создание новой записи устройства");
        return ResponseEntity.ok(deviceService.create(createDeviceDto));
    }

    /**
     * Получение информации об устройстве.
     *
     * @param id идентификатор устройства.
     * @return информация об устройстве.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> get(@PathVariable("id") UUID id) {
        log.info("Получение информации об устройстве с ID {}", id);
        return ResponseEntity.ok(deviceService.get(id));
    }

    /**
     * Получение информации об имеющихся устройствах.
     *
     * @return информация об имеющихся устройствах.
     */
    @GetMapping("/")
    public ResponseEntity<List<DeviceDto>> getAll() {
        log.info("Получение информации об имеющихся устройствах");
        return ResponseEntity.ok(deviceService.getAll());
    }

    /**
     * Обновление информации об устройстве.
     *
     * @param id идентификатор устройства.
     * @param updateDeviceDto информация об устройстве для обновления.
     * @return обновленная информация об устройстве.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> update(@PathVariable("id") UUID id, @RequestBody UpdateDeviceDto updateDeviceDto) {
        log.info("Обновление информации устройства c ID {}", id);
        return ResponseEntity.ok(deviceService.update(id, updateDeviceDto));
    }

    /**
     * Удаление информации об устройстве.
     *
     * @param id идентификатор устройства.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Удаление информации об устройстве c ID {}", id);
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Обновление статуса устройства.
     *
     * @param id идентификатор устройства.
     * @param status статус устройства.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> setStatus(@PathVariable("id") UUID id, @RequestBody String status) {
        log.info("Изменение статуса устройства с ID {}", id);
        deviceService.setStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    /**
     * Отправка команды на устройство.
     *
     * @param id идентификатор устройства.
     * @param command команда.
     */
    @PostMapping("/{id}/command")
    public ResponseEntity<Void> execute(@PathVariable("id") UUID id, @RequestBody String command) {
        log.info("Отправка команды [ {} ] на устройство c ID {}", id, command);
        deviceService.execute(id, command);
        return ResponseEntity.noContent().build();
    }
}
