package ru.podgoretskaya.occurrenceOfSymbols.service;

import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.util.Map;

public interface CountingSymbols {
    Map<Character, Integer> countingSymbols(InputString dto);
}
