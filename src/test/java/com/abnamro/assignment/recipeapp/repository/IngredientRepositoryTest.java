package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class IngredientRepositoryTest {

    @Mock
    IngredientRepository ingredientRepository;

    Set<String> ingredients;

    @BeforeEach
    void setUp() {
        ingredients = new HashSet<>();
        ingredients.add("dried dill");
        ingredients.add("oregano, dried");
    }

    @Test
    void findDistinctByDescriptionIn() {
        Set<Ingredient> distinctByDescriptionIn = ingredientRepository.findDistinctByDescriptionIn(ingredients);
        int size = distinctByDescriptionIn.size();
        System.out.println(size);
    }
}