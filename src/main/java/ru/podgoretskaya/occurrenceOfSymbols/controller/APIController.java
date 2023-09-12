package ru.podgoretskaya.occurrenceOfSymbols.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;
import ru.podgoretskaya.occurrenceOfSymbols.service.CountingSymbols;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Вычисление частоты встречи символов в строке")
public class APIController {
    private final CountingSymbols countingSymbols;

    @PostMapping(value = "/in")
    public ResponseEntity<Map<Character, Integer>> getSymbols(@Valid @RequestBody InputString model) {
        log.info("\n>>>>>>> Параметры: \"" + model.toString() + "<<<<<<" + "\n");
        return new ResponseEntity<>(countingSymbols.countingSymbols(model), HttpStatus.OK);
    }
}
