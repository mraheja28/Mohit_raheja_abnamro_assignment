package com.abnamro.assignment.recipeapp.repository;

import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Transactional
@SpringBootTest
class RecipeRepositoryTest {

    @Mock
    RecipeRepository recipeRepository;

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


        System.out.println("saved");
    }

    @Test
    void findRecipeByVegetarian() {
        recipeRepository.save(recipe);
        List<Recipe> recipeByVegetarian = recipeRepository.findRecipeByVegetarian(true);
        if (recipeByVegetarian.size() > 0) {
            Recipe recipe = recipeByVegetarian.get(0);
            assertTrue(recipe.isVegetarian());
        }
    }

    @Test
    void findRecipesByDescriptionContains() {
    }

    @Test
    void findRecipeByServings() {
    }

    @Test
    void findRecipeById() {
    }

    @Test
    void deleteRecipeById() {
    }
}