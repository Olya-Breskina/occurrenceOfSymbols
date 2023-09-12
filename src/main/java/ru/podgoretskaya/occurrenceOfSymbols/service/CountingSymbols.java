package ru.podgoretskaya.occurrenceOfSymbols.service;

import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.util.HashMap;

public interface CountingSymbols {
    HashMap<Character,Integer> countingSymbols(InputString dto);
}
