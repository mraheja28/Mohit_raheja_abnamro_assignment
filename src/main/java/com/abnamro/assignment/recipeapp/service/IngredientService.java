package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.convertor.IngredientConvertor;
import com.abnamro.assignment.recipeapp.convertor.RecipeConvertor;
import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientConvertor ingredientConvertor;
    private final RecipeConvertor recipeConvertor;

    public IngredientService(IngredientRepository ingredientRepository, IngredientConvertor ingredientConvertor,
                             RecipeConvertor recipeConvertor) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientConvertor = ingredientConvertor;
        this.recipeConvertor = recipeConvertor;
    }

    public Set<RecipeDTO> findRecipeContainingIngredients(Set<IngredientDTO> ingredientDTOS) {
        Set<String> ingredientDesc = new HashSet<>();

        ingredientDTOS.forEach(ingredientDTO -> ingredientDesc.add(ingredientDTO.getDescription()));
        Set<Ingredient> allByDescription = ingredientRepository.findDistinctByDescriptionIn(ingredientDesc);
        Set<RecipeDTO> recipeDTOS = new HashSet<>();
        allByDescription.forEach(ingredient -> recipeDTOS.add(recipeConvertor.convertToDTO(ingredient.getRecipe())));

        return recipeDTOS;
    }

    public Set<RecipeDTO> findRecipeNotContainingIngredients(Set<IngredientDTO> ingredientDTOS) {
        Set<String> ingredientDesc = new HashSet<>();
        ingredientDTOS.forEach(ingredientDTO -> ingredientDesc.add(ingredientDTO.getDescription()));
        Set<Ingredient> distinctByDescriptionNotIn = ingredientRepository.findDistinctByDescriptionNotIn(ingredientDesc);
        Set<Ingredient> distinctByDescriptionIn = ingredientRepository.findDistinctByDescriptionIn(ingredientDesc);
        Set<Long> recipeIdsNotIn = new HashSet<>();
        distinctByDescriptionNotIn.forEach(ingredient -> recipeIdsNotIn.add(ingredient.getRecipe().getId()));
        Set<Long> recipeIdsIn = new HashSet<>();
        distinctByDescriptionIn.forEach(ingredient -> recipeIdsIn.add(ingredient.getRecipe().getId()));

        Set<RecipeDTO> recipes = new HashSet<>();
        for (long id : recipeIdsNotIn) {
            for (Ingredient ingredient : distinctByDescriptionNotIn) {
                if (!recipeIdsIn.contains(id) && ingredient.getRecipe().getId() == id) {
                    recipes.add(recipeConvertor.convertToDTO(ingredient.getRecipe()));
                    break;
                }
            }
        }
        return  recipes;
    }

    public void addIngredient(IngredientDTO ingredientDTO) {
        ingredientRepository.save(ingredientConvertor.convertFromDTO(ingredientDTO));
    }

    @Transactional
    public int removeIngredient(long id) {
        return ingredientRepository.deleteIngredientById(id);
    }


}
