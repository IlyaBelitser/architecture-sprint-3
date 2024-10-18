package ru.yandex.practicum.device.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.device.exception.badRequest.*;
import ru.yandex.practicum.device.exception.notFound.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<String> handleDeviceNotFoundException(DeviceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DeviceTypeNotFoundException.class)
    public ResponseEntity<String> handleDeviceTypeNotFoundException(DeviceTypeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ModuleNotFoundException.class)
    public ResponseEntity<String> handleModuleNotFoundException(ModuleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ScenarioNotFoundException.class)
    public ResponseEntity<String> handleScenarioNotFoundException(ScenarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CommandNotFoundException.class)
    public ResponseEntity<String> handleCommandNotFoundException(CommandNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DeviceBadRequestException.class)
    public ResponseEntity<String> handleDeviceBadRequestException(DeviceBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DeviceTypeBadRequestException.class)
    public ResponseEntity<String> handleDeviceTypeBadRequestException(DeviceTypeBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ModuleBadRequestException.class)
    public ResponseEntity<String> handleModuleBadRequestException(ModuleBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ScenarioBadRequestException.class)
    public ResponseEntity<String> handleScenarioBadRequestException(ScenarioBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TelemetryBadRequestException.class)
    public ResponseEntity<String> handleTelemetryBadRequestException(TelemetryBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
