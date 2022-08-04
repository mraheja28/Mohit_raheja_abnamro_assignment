package com.abnamro.assignment.recipeapp.convertor;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class RecipeConvertor {

    private final IngredientConvertor ingredientConvertor;

    public RecipeConvertor(IngredientConvertor ingredientConvertor) {
        this.ingredientConvertor = ingredientConvertor;
    }

    public RecipeDTO convertToDTO(Recipe recipe) {
        if (recipe == null) return null;

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipe.getId());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setVegetarian(recipe.isVegetarian());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setCookTime(recipe.getCookTime());
        recipe.getIngredients().forEach(ingredient ->
                recipeDTO.getIngredients().add(ingredientConvertor.convertToDTO(ingredient)));

        return  recipeDTO;
    }

    public Recipe convertFromDTO(RecipeDTO recipeDTO) {
        if(recipeDTO == null) return null;

        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getRecipeId());
        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setVegetarian(recipeDTO.isVegetarian());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setServings(recipeDTO.getServings());
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setDescription(ingredientDTO.getDescription());
            ingredient.setAmount("" + ingredientDTO.getAmount());
            ingredient.setMeasurementUnit(ingredientDTO.getMeasurementUnit());
            ingredient.setRecipe(recipe);
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        return recipe;
    }
}
