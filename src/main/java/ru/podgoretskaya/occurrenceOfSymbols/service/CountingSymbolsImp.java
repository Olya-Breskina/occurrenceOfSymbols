package ru.podgoretskaya.occurrenceOfSymbols.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.util.*;
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
        Matcher stringSatisfiesTheCondition = patlatletter.matcher(dto.getString());
        if (!stringSatisfiesTheCondition.matches()) {
            log.info("\n>>>>>>> проверьте строку, она не должна содержать пробелов и знаков припенания: " + dto.getString() + "<<<<<<" + "\n");
            throw new IllegalArgumentException("проверьте строку");
        } else {
            String str = dto.getString();
            HashMap<Character, Integer> map = new HashMap<>();
            char[] ch = str.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                if (map.containsKey(ch[i])) {
                    map.replace(ch[i], map.get(ch[i]) + 1);
                } else
                    map.put(ch[i], 1);
            }

            log.debug("\n>>>>>>> частота встречи каждого символа: " + map + "<<<<<<" + "\n");
            return  map
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(
                            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new));
        }
    }
}
