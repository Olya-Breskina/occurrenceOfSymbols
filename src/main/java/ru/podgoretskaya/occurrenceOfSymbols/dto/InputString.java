package ru.podgoretskaya.occurrenceOfSymbols.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputString {
    @Schema(description = "исходная строка, может содержать набор из букв и цифр (латиница + кириллица)")
    private String inputString;
}
