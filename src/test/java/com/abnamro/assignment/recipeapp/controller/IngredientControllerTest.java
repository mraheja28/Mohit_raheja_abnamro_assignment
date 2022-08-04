package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IngredientControllerTest {

    @Autowired
    IngredientController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Set<IngredientDTO> ingredientDTOS;

    @BeforeEach
    void setUp() {
        ingredientDTOS = new HashSet<>();
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setDescription("olive oil");
        ingredientDTOS.add(ingredientDTO);
    }

    @Test
    void findRecipeContainingIngredients() {
        MultiValueMap<String, Set<IngredientDTO>> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("ingredients", ingredientDTOS);
        HttpEntity<MultiValueMap<String, Set<IngredientDTO>>> entity =
                new HttpEntity<>(multiValueMap);
        String recsult = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/recipe-containing-ingredients", String.class);
        System.out.println("service was invoked successfully. " + recsult);
    }

    @Test
    void findRecipeNotContainingIngredients() {
        String recsult = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/recipe-containing-ingredients", String.class, ingredientDTOS);
        System.out.println("service was invoked successfully. " + recsult);
    }

    @Test
    void newIngredient() {
    }

    @Test
    void removeIngredient() {
    }
}