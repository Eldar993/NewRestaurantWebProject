package com.example.restaurant.controller;


import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAllIngredient(ModelAndView mav) {
        List<Ingredient> ingredient = ingredientService.printIngredients();
        mav.setViewName("ingredients");
        mav.addObject("ingredientList", ingredient);

        return mav;
    }

    @RequestMapping(value = "/createIngredient", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createIngredientForm(@ModelAttribute ModelAndView mav) {

        //new Ingredients()
        //id == null
        //title == null
        //calories == 0
        mav.setViewName("createIngredient");
        mav.addObject("myIngredient", new Ingredient());
        mav.addObject("actionType", "create");
        return mav;
    }

    @RequestMapping(value = "/createIngredient", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createIngredient(@ModelAttribute("ingredient") Ingredient ingredient, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            mav.setViewName("/createIngredient");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("actionType", "create");
            mav.addObject("ingredient", ingredient);
            return mav;
        }


        ingredientService.create(ingredient);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        mav.setView(redirectView);
        return mav;
    }

    @RequestMapping(value = "/updateIngredient/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateIngredientForm(@PathVariable("id") Long id, ModelAndView mav) {

        mav.setViewName("/updateIngredient");
        Ingredient ingredient = ingredientService.findIngredient(id);
        mav.addObject("ingredient", ingredient);

        return mav;
    }

    @RequestMapping(value = "/updateIngredient/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView updateIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, ModelAndView mav) {


        Ingredient updatedIngredient = ingredientService.update(ingredient);


        mav.setViewName("/updateIngredient");

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        return redirectView;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, ModelAndView mav) {
        ingredientService.deleteIngredient(id);
        mav.setViewName("/ingredients");
        //return mav;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        return redirectView;
    }
}
