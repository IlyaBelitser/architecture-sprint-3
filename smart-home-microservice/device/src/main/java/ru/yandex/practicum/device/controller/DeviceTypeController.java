package ru.yandex.practicum.device.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.device.dto.deviceType.CreateDeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.DeviceTypeDto;
import ru.yandex.practicum.device.dto.deviceType.UpdateDeviceTypeDto;
import ru.yandex.practicum.device.service.DeviceTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/deviceTypes")
@RequiredArgsConstructor
public class DeviceTypeController {
    private final DeviceTypeService deviceTypeService;
    private static final Logger log = LoggerFactory.getLogger(DeviceTypeController.class);

    /**
     * Создание новой записи типа устройства.
     *
     * @param createDeviceTypeDto данные для сохранения типа устройства.
     * @return сохранённая запись о типа устройства.
     */
    @PostMapping("/")
    public ResponseEntity<DeviceTypeDto> create(@RequestBody CreateDeviceTypeDto createDeviceTypeDto) {
        log.info("Создание новой записи типа устройства");
        return ResponseEntity.ok(deviceTypeService.create(createDeviceTypeDto));
    }

    /**
     * Получение информации об имеющихся типах устройств.
     *
     * @return информация об имеющихся типах устройств.
     */
    @GetMapping("/")
    public ResponseEntity<List<DeviceTypeDto>> getAll() {
        log.info("Получение информации об имеющихся типах устройств");
        return ResponseEntity.ok(deviceTypeService.getAll());
    }

    /**
     * Получение информации о типе устройства.
     *
     * @param id идентификатор типа устройства.
     * @return информация о типе устройства.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeviceTypeDto> get(@PathVariable("id") Long id) {
        log.info("Получение информации о типе устройства с ID {}", id);
        return ResponseEntity.ok(deviceTypeService.get(id));
    }

    /**
     * Обновление информации типа устройства.
     *
     * @param id идентификатор типа устройства.
     * @param updateDeviceTypeDto информация о типе устройства для обновления.
     * @return обновленная информация о типе устройства.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeviceTypeDto> update(@PathVariable("id") Long id, @RequestBody UpdateDeviceTypeDto updateDeviceTypeDto) {
        log.info("Обновление информации типа устройства c ID {}", id);
        return ResponseEntity.ok(deviceTypeService.update(id, updateDeviceTypeDto));
    }

    /**
     * Удаление информации типа устройства.
     *
     * @param id идентификатор типа устройства.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Удаление информации типа устройства c ID {}", id);
        deviceTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
