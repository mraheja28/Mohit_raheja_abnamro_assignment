package com.abnamro.assignment.recipeapp.convertor;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import org.springframework.stereotype.Component;


@Component
public class IngredientConvertor {

    public IngredientDTO convertToDTO(Ingredient ingredient) {
        if (ingredient == null) return null;

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setRecipeId(ingredient.getRecipe().getId());
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setMeasurementUnit(ingredient.getMeasurementUnit());

        return ingredientDTO;
    }

    public Ingredient convertFromDTO(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null) return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setMeasurementUnit(ingredientDTO.getMeasurementUnit());
        if (ingredientDTO.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(ingredientDTO.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }
        return ingredient;
    }
}
