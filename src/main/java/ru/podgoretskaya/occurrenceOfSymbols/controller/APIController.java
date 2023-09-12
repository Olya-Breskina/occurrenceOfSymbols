package ru.podgoretskaya.occurrenceOfSymbols.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;
import ru.podgoretskaya.occurrenceOfSymbols.service.CountingSymbols;

import java.util.HashMap;

@Controller
@Slf4j
@SuppressWarnings("unused")
@Tag(name = "Вычисление частоты встречи символов в строке")
public class APIController {
    private final CountingSymbols countingSymbols;
    InputString inputString = new InputString();

    public APIController(CountingSymbols countingSymbols) {
        this.countingSymbols = countingSymbols;
    }

    @PostMapping(value = "/in")
    public ResponseEntity<HashMap<Character, Integer>> getOffersPages(@Parameter @RequestBody InputString model) {
        log.info(" Параметры: \"" + model.toString());
        try {
            return new ResponseEntity<HashMap<Character, Integer>>(countingSymbols.countingSymbols(model), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.info("ошибка заполнения формы");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
