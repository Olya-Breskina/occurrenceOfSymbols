package ru.podgoretskaya.occurrenceOfSymbols.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@AllArgsConstructor
public class CountingSymbolsImp implements CountingSymbols {
    @Override
    public Map<Character, Integer> countingSymbols(InputString dto) {
        Pattern patlatletter = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher stringSatisfiesTheCondition = patlatletter.matcher(dto.getInputString());
        if (!stringSatisfiesTheCondition.matches()) {
            log.info("\n>>>>>>> проверьте строку, она не должна содержать пробелов и знаков припенания: " + dto.getInputString() + "<<<<<<" + "\n");
            throw new HttpMessageNotReadableException("проверьте строку");
        } else {
            String str = dto.getInputString();
            HashMap<Character, Integer> map = new HashMap<>();
            char[] ch = str.toCharArray();
            for (char c : ch) {
                if (map.containsKey(c)) {
                    map.replace(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }

            log.debug("\n>>>>>>> частота встречи каждого символа: " + map + "<<<<<<" + "\n");
            return map
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(
                            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new));
        }
    }
}
