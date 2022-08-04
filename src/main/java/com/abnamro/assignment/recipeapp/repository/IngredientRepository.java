package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Set<Ingredient> findDistinctByDescriptionIn(Iterable<String> description);

    Set<Ingredient> findDistinctByDescriptionNotIn(Iterable<String> description);

    Integer deleteIngredientById(Long id);
}
