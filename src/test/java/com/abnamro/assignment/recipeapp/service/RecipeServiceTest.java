package com.abnamro.assignment.recipeapp.service;

import com.abnamro.assignment.recipeapp.convertor.RecipeConvertor;
import com.abnamro.assignment.recipeapp.domain.Ingredient;
import com.abnamro.assignment.recipeapp.domain.Recipe;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeService recipeService;

    @Mock
    RecipeConvertor recipeConvertor;

    Recipe recipe;

    @BeforeEach
    public void setUp() {
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
    void findRecipeByVegetarian() {
        boolean flag = false;
        List<RecipeDTO> recipeByVegetarian = recipeService.findRecipeByVegetarian(flag);
        if (recipeByVegetarian.size() > 0) {
            RecipeDTO recipe = recipeByVegetarian.iterator().next();
            assertEquals(flag, recipe.isVegetarian());
        }
    }

    @Test
    void findRecipeByInstruction() {
        String text = "some";
        List<Recipe> recipesByDescriptionContains = recipeRepository.findRecipesByDescriptionContains(text);


    }

    @Test
    void findRecipeByServing() {
        List<Recipe> recipeByServings = recipeRepository.findRecipeByServings(4);
    }

    @Test
    void findRecipeById() {
        verify(recipeRepository, times(1)).findRecipeById(anyLong());
    }

    @Test
    void saveRecipe() {
        RecipeDTO recipeDTO = recipeConvertor.convertToDTO(this.recipe);
        Recipe savedRecipe = recipeService.saveRecipe(recipeDTO);
        System.out.println(savedRecipe.getId());
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void removeRecipeById() {
    }
}