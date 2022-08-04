package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipeByVegetarian(boolean vegetarian);

    List<Recipe> findRecipesByDescriptionContains(String description);

    List<Recipe> findRecipeByServings(Integer servings);

    List<Recipe> findDistinctFirstByIngredientsNotIn(Iterable<Ingredient> ingredients);

    Recipe findRecipeById(Long recipeId);

    int deleteRecipeById(Long id);
    
    @Query("SELECT r FROM Ingredient i JOIN Recipe r ON i.recipe.id = r.id where i.description IN (?1) ")
    List<Recipe> getByIngredientsIncludes(Collection<String> includes);
    
    @Query("SELECT r FROM Ingredient i JOIN Recipe r ON i.recipe.id = r.id where i.description NOT IN (?1) ")
    //@Query("SELECT r FROM Ingredient i, Recipe r where i.recipe.id = r.id and NOT EXISTS (SELECT ing from Ingredient ing where ing.description = (?1)) ")
    List<Recipe> getByIngredientsExcludes(Collection<String> excludes);

    List<Recipe> findDistinctByDescriptionContainsAllIgnoreCaseOrderByNameAsc(String instructions);
}
