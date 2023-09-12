package ru.podgoretskaya.occurrenceOfSymbols.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor
public class CountingSymbolsImp implements CountingSymbols {
    @Override
    public HashMap<Character, Integer> countingSymbols(InputString dto) {
        HashMap<Character, Integer> map = new HashMap<>();
        String str = dto.getString();
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (map.containsKey(ch[i])) {
                map.replace(ch[i], map.get(ch[i]) + 1);
            } else
                map.put(ch[i], 1);
        }
        return map;
    }
}
