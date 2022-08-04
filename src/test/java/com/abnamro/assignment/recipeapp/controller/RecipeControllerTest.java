package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.service.IngredientService;
import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Autowired
    private ObjectMapper objectMapper;

    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setServings(6);
        recipe.setName("Taco Soup");
        recipe.setCookTime(10);
        recipe.setVegetarian(true);
        recipe.setDescription("What’s easier than taco night? Taco SOUP night! Rather than fussing around " +
                "with warming tortillas in one pan, and seasoned beef sizzling in another, we’ve taken the ease " +
                "of taco night and made it even better by soupifying it!");
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe
                .addIngredient(ingredient1)
                .addIngredient(ingredient2)
                .addIngredient(ingredient3);
    }

    @Test
    void listAllRecipe() {
    }

    @Test
    void findRecipeByVegetarian() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/v1/find-recipe-by-vegetarian?vegetarian=0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RecipeDTO())));
        System.out.println("done");
    }

    @Test
    void findRecipeByInstruction() {
    }

    @Test
    void addRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }

    @Test
    void handleError() {
    }

    @Test
    void handleMediaTypeError() {
    }

    @Test
    void handleNotFoundException() {
    }

    @Test
    void handleBindingError() {
    }

    @Test
    void handleJsonError() {
    }
}