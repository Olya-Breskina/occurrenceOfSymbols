package ru.podgoretskaya.occurrenceOfSymbols.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputString {
    @Schema(description = "исходная строка")
    @NotNull(message = "поле не должно быть пустым")
    String string;
}
