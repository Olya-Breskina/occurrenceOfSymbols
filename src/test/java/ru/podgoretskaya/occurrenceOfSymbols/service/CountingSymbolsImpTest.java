package ru.podgoretskaya.occurrenceOfSymbols.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ru.podgoretskaya.occurrenceOfSymbols.dto.InputString;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CountingSymbolsImpTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Spy
    CountingSymbolsImp countingSymbols = new CountingSymbolsImp();

    @Test
    void test1() throws Exception {
        InputString dto = objectMapper.readValue(new File("src/test/resources/test.json"), InputString.class);
        Map<Character, Integer> characterIntegerMap = countingSymbols.countingSymbols(dto);
        Map<Character, Integer> result = new LinkedHashMap<>();
        result.put('1', 1);
        result.put('v', 2);
        result.put('ц', 2);
        result.put('q', 1);
        result.put('F', 1);
        result.put('ъ', 1);

        assertNotNull(characterIntegerMap);
        assertThat(characterIntegerMap.size(), is(6));
        assertThat(characterIntegerMap, is(result));
        assertThat(characterIntegerMap, IsMapContaining.hasEntry('F', 1));
    }

    @Test
    void getBedStrung() throws Exception {
        InputString dto = objectMapper.readValue(new File("src/test/resources/testBed.json"), InputString.class);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                countingSymbols.countingSymbols(dto));
        assertEquals("проверьте строку", illegalArgumentException.getMessage());
    }



}
