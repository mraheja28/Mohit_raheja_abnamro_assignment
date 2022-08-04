package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.service.IngredientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IngredientController.class)
class IngredientControllerTestMock {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IngredientService ingredientService;

    Set<IngredientDTO> ingredientDTOS;

    @BeforeEach
    void setUp() {
        ingredientDTOS = new HashSet<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setDescription("olive oil");
        ingredientDTOS.add(ingredientDTO);
    }

    @Test
    void findRecipeContainingIngredients() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(ingredientDTOS);
        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/recipe-containing-ingredients").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    void newIngredient() {
    }

    @Test
    void removeIngredient() {
    }
}