package com.abnamro.assignment.recipeapp.convertor;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.MeasurementUnit;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class IngredientConvertorTest {

    @InjectMocks
    IngredientConvertor ingredientConvertor;

    Ingredient ingredient;

    Recipe recipe;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setAmount("2");
        ingredient.setDescription("test data");

        MeasurementUnit measurementUnit = new MeasurementUnit();
        measurementUnit.setId(1L);
        measurementUnit.setDescription("tsp");

        recipe = new Recipe();
        recipe.setId(1L);
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

        ingredient.setMeasurementUnit(measurementUnit);
        ingredient.setRecipe(recipe);
    }

    @Test
    void convertToDTO() {
        IngredientDTO ingredientDTO = ingredientConvertor.convertToDTO(ingredient);
        String description = ingredientDTO.getDescription();
        assertEquals("test data", description);
    }

    @Test
    void convertFromDTO() {
        IngredientDTO ingredientDTO = ingredientConvertor.convertToDTO(ingredient);
        Ingredient ingredient = ingredientConvertor.convertFromDTO(ingredientDTO);
        assertEquals("test data", ingredient.getDescription());
    }
}