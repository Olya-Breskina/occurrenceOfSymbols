package ru.podgoretskaya.occurrenceOfSymbols.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.podgoretskaya.occurrenceOfSymbols.service.CountingSymbols;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIController.class)
class APIControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CountingSymbols countingSymbols;

    @Test
    void getGoodStrung() throws Exception {
        //исходная строка содержить набор из букв и цифр (латиница + кириллица)
        mockMvc.perform(post("/in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                            {
                                                 "inputString": "Fvvqцъц1"
                                            }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void getBedStrung() throws Exception {
        //исходная строка содержить знаки припенания
        when(countingSymbols.countingSymbols(any())).thenThrow(new IllegalArgumentException());
        mockMvc.perform(post("/in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                            {
                                                 "inputString": "Fvvq,цъц1"
                                            }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getEmptyString() throws Exception {
        //исходная строка пустая
        when(countingSymbols.countingSymbols(any())).thenThrow(new IllegalArgumentException());
        mockMvc.perform(post("/in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}