package com.example.restaurant.service;

import com.example.restaurant.entity.Ingredients;
import com.example.restaurant.entity.User;
import com.example.restaurant.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;


    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public boolean create(Ingredients ingredient) {
        ingredientsRepository.saveAndFlush(ingredient);
        return true;
    }

    public List<Ingredients> printIngredients() {
        List<Ingredients> Ingredients = ingredientsRepository.findAll();
        return Ingredients;
    }

    public Ingredients findIngredient(Long id){
       Ingredients ingredientInfo = ingredientsRepository.findIngredientById(id);
       return ingredientInfo;
    }
    public void deleteAll() {
        ingredientsRepository.deleteAll();
    }

    public void deleteIngredient(Long id) {

        ingredientsRepository.deleteById(id);


    }

    public Ingredients update(Ingredients ingredient) {
        Ingredients updatedIngredient = ingredientsRepository.findIngredientById(ingredient.getId());
        if (updatedIngredient == null) {
            return null;
        }
        updatedIngredient.setTitle(ingredient.getTitle());
        ingredientsRepository.saveAndFlush(updatedIngredient);
        return updatedIngredient;
    }
}
