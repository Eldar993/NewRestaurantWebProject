package com.example.restaurant.controller;


import com.example.restaurant.dto.IngredientDto;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.service.IngredientsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IngredientsController {
    private final IngredientsService ingredientService;

    public IngredientsController(IngredientsService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {
        List<IngredientDto> ingredient = ingredientService.findAll();
        mav.setViewName("/ingredients/ingredients");
        mav.addObject("allIngredients", ingredient);

        return mav;
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute ModelAndView mav) {

        mav.setViewName("/ingredients/ingredientForm");
        mav.addObject("ingredient", new Ingredient());
        mav.addObject("actionType", "create");

        return mav;
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("ingredient") @Valid IngredientDto ingredientDto,
                               BindingResult bindingResult,
                               ModelAndView mav) {

        if (bindingResult.hasErrors()) {
            mav.setViewName("/ingredients/ingredientForm");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("actionType", "create");
            mav.addObject("ingredient", ingredientDto);

            return mav;
        }

        final boolean result = ingredientService.create(ingredientDto);
        if (result) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/ingredients");
            mav.setView(redirectView);

            return mav;
        }

        mav.addObject("errorMessage", "Object not created");
        mav.addObject("actionType", "create");
        mav.addObject("ingredient", ingredientDto);

        return mav;
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id, ModelAndView mav) {

        mav.setViewName("/ingredients/ingredientForm");
        //TODO: replace ingredientService.toDto() to static method calling
        IngredientDto ingredient = ingredientService.toDto(ingredientService.findById(id));
        mav.addObject("ingredient", ingredient);

        return mav;
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id,
                               @ModelAttribute("ingredient") @Valid IngredientDto ingredientDto,
                               BindingResult result,
                               ModelAndView mav) {
        ingredientDto.setId(id);
        if (result.hasErrors()) {
            mav.setViewName("/ingredients/ingredientForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("ingredient", ingredientDto);

            return mav;
        }

        IngredientDto updatedIngredient = ingredientService.update(ingredientDto);
        if (updatedIngredient == null) {
            mav.setViewName("/ingredients/ingredientForm");
            mav.addObject("generalError", "Some error occurred");

            return mav;
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        mav.setView(redirectView);

        return mav;
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") Long id) {
        ingredientService.deleteById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");

        return redirectView;
    }
}
