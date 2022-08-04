package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.service.IngredientService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("recipe-containing-ingredients")
    public String findRecipeContainingIngredients(@RequestBody Set<IngredientDTO> ingredients) {
        Set<RecipeDTO> allRecipe = ingredientService.findRecipeContainingIngredients(ingredients);
        return new Gson().toJson(allRecipe);
    }

    @GetMapping("recipe-not-containing-ingredients")
    public String findRecipeNotContainingIngredients(@RequestBody Set<IngredientDTO> ingredients) {
        Set<RecipeDTO> allRecipe = ingredientService.findRecipeNotContainingIngredients(ingredients);
        return new Gson().toJson(allRecipe);
    }

    @PostMapping("/new-ingredient")
    public ResponseEntity<String> newIngredient(@RequestBody IngredientDTO ingredientDTO) {
        ingredientService.addIngredient(ingredientDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/remove-ingredient")
    public String removeIngredient(@RequestParam("id") long id) {
        int i = ingredientService.removeIngredient(id);
        if (i > 0)
        return "It's deleted successfully";
        else
            return "There is nothing to remove with id: " + id;
    }


}
