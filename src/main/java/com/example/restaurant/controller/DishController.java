package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.DishTypeDto;
import com.example.restaurant.dto.IngredientDto;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.DishTypeService;
import com.example.restaurant.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishTypeService dishTypeService;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {

        mav.setViewName("/Dishes/dishes");
        List<DishDto> dishList = DishService.toDto(dishService.findAll());

        mav.setViewName("/Dishes/dishes");
        mav.addObject("dishList", dishList);

        return mav;

    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView mav) {
        List<DishTypeDto> dishTypeList = DishTypeService.toDto(dishTypeService.findAll());

        mav.setViewName("/Dishes/dishForm");
        mav.addObject("dish", new DishDto());
        mav.addObject("dishTypeList",dishTypeList);

        mav.addObject("actionType", "create");
        return mav;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("dish") @Valid DishDto dishDto,DishType dishType, BindingResult result, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("/Dishes/dishForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("actionType", "create");
            mav.addObject("dish", dishDto);

        } else {
            mav.addObject("dishType", dishType);
            Dish entity = dishService.toEntity(dishDto);
            dishService.create(entity);

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/dishes");
            mav.setView(redirectView);

        }
        return mav;

    }

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id, ModelAndView mav) {

        mav.setViewName("/Dishes/dishForm");
        DishDto dish = DishService.toDto(dishService.findById(id));
        mav.addObject("dish", dish);

        return mav;
    }

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("dish") @Valid DishDto dishDto, BindingResult result, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("/Dishes/dishForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("dish", dishDto);
        } else {
            Dish entity = dishService.toEntity(dishDto);
            dishService.update(entity);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/dishes");
            mav.setView(redirectView);
        }
        return mav;

    }

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") Long id) {
        dishService.deleteById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/dishes");
        return redirectView;
    }

    @RequestMapping(value = "/dish/{id}/ingredient",method = RequestMethod.GET)
    public ModelAndView dishIngredientForm(@PathVariable("id") Long id,ModelAndView mav){
        mav.setViewName("/Dishes/dishIngredient");


        return mav;
    }
    @RequestMapping(value = "/dish/{id}/ingredient",method = RequestMethod.PUT)
    public ModelAndView addIngredient(@PathVariable("id") Long id, ModelAndView mav,DishDto dishDto, IngredientDto ingredientDto) {
        mav.addObject("actionType", "add");
        mav.addObject("ingredient", ingredientDto);
        mav.addObject("dish", dishDto);

        Dish entity = dishService.toEntity(dishDto);
        Ingredient ingredient = IngredientService.toEntity(ingredientDto);
        entity.addIngredient(ingredient);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/dishes");
        mav.setView(redirectView);

        return mav;
    }

    @RequestMapping(value = "/dish/{id}/ingredient",method = RequestMethod.DELETE)
    public ModelAndView removeIngredient(@PathVariable("id") Long id, ModelAndView mav,DishDto dishDto, IngredientDto ingredientDto) {
        mav.addObject("actionType", "delete");
        mav.addObject("ingredient", ingredientDto);
        mav.addObject("dish", dishDto);

        Dish entity = dishService.toEntity(dishDto);
        Ingredient ingredient = IngredientService.toEntity(ingredientDto);
        entity.removeIngredient(ingredient);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/dishes");
        mav.setView(redirectView);

        return mav;
    }


}
