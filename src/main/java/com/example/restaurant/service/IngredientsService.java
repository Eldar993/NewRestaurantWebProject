package com.example.restaurant.service;

import com.example.restaurant.dto.IngredientDto;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientsService {

    private final IngredientRepository ingredientRepository;


    public IngredientsService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public boolean create(Ingredient ingredient) {
        if (ingredient.getId() != null) {
            return false;
        }
        ingredientRepository.saveAndFlush(ingredient);
        return true;
    }

    public List<Ingredient> printIngredients() {
        List<Ingredient> Ingredients = ingredientRepository.findAll();
        return Ingredients;
    }

    public Ingredient findIngredient(Long id) {
        Ingredient ingredientInfo = ingredientRepository.findIngredientById(id);
        return ingredientInfo;
    }

    public void deleteAll() {
        ingredientRepository.deleteAll();
    }

    public void deleteIngredient(Long id) {

        ingredientRepository.deleteById(id);


    }

    public Ingredient update(Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientRepository.findIngredientById(ingredient.getId());
        if (updatedIngredient == null) {
            return null;
        }
        updatedIngredient.setTitle(ingredient.getTitle());
        updatedIngredient.setCalories(ingredient.getCalories());
        ingredientRepository.saveAndFlush(updatedIngredient);
        return updatedIngredient;
    }
}
