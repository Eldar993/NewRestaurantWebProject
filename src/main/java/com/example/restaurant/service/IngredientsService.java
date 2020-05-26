package com.example.restaurant.service;

import com.example.restaurant.dto.IngredientDto;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
//TODO: Change returned types and input methods arguments from Dto to Entity
//      convert entity to dto in Controller layer
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

    public List<Ingredient> findAll() {


        return ingredientRepository.findAll();
    }

    public Ingredient findById(Long id) {
        Ingredient ingredient = ingredientRepository.findIngredientById(id);
        return ingredient;
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public Ingredient update(Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientRepository.findIngredientById(ingredient.getId());
        if (updatedIngredient == null) {
            return null;
        }
        updatedIngredient.setTitle(ingredient.getTitle());
        updatedIngredient.setCalories(ingredient.getCalories());

        final Ingredient result = ingredientRepository.saveAndFlush(updatedIngredient);
        return result;
    }

    //TODO: make converter's methods static
    public static Ingredient toEntity(IngredientDto dto) {
        if (dto == null) {
            return null;
        }
        Ingredient result = new Ingredient();
        result.setId(dto.getId());
        result.setTitle(dto.getTitle());
        result.setCalories(dto.getCalories());

        return result;
    }

    public  static List<IngredientDto> toDto(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static IngredientDto toDto(Ingredient entity) {
        if (entity == null) {
            return null;
        }
        IngredientDto result = new IngredientDto();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setCalories(entity.getCalories());

        return result;
    }
}
