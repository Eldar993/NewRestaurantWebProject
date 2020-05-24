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

    public boolean create(IngredientDto ingredient) {
        if (ingredient.getId() != null) {
            return false;
        }
        ingredientRepository.saveAndFlush(toEntity(ingredient));
        return true;
    }

    public List<IngredientDto> findAll() {
//        List<Ingredient> ingredients = ingredientRepository.findAll();
//        List<IngredientDto> result = new LinkedList<>();
//        for (Ingredient ingredient : ingredients) {
//            result.add(toDto(ingredient));
//        }
//        return result;

        return toDto(ingredientRepository.findAll());
    }

    public Ingredient findById(Long id) {
        Ingredient ingredient = ingredientRepository.findIngredientById(id);
        return ingredient;
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public IngredientDto update(IngredientDto ingredientDto) {
        Ingredient updatedIngredient = ingredientRepository.findIngredientById(ingredientDto.getId());
        if (updatedIngredient == null) {
            return null;
        }
        updatedIngredient.setTitle(ingredientDto.getTitle());
        updatedIngredient.setCalories(ingredientDto.getCalories());

        final Ingredient result = ingredientRepository.saveAndFlush(updatedIngredient);
        return toDto(result);
    }

    //TODO: make converter's methods static
    public Ingredient toEntity(IngredientDto dto) {
        if (dto == null) {
            return null;
        }
        Ingredient result = new Ingredient();
        result.setId(dto.getId());
        result.setTitle(dto.getTitle());
        result.setCalories(dto.getCalories());

        return result;
    }

    public List<IngredientDto> toDto(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public IngredientDto toDto(Ingredient entity) {
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
