package ru.podgoretskaya.occurrenceOfSymbols.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ServiceException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> newException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        String apiError = new String("Строка не прошла валидацию. Проверьте строку, она не должна содержать пробелов и знаков припенания.");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
