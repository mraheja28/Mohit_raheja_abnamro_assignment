package com.abnamro.assignment.recipeapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RecipeDTO implements Serializable {

    private Long recipeId;
    private String name;
    private String description;
    private Integer cookTime;
    private Integer servings;
    private boolean vegetarian;
    private Set<IngredientDTO> ingredients;

    public RecipeDTO() {
        ingredients  = new HashSet<>();
    }

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Set<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}
    
}
