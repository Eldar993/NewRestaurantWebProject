package com.example.restaurant.controller;


import com.example.restaurant.entity.Ingredients;
import com.example.restaurant.repository.IngredientsRepository;

import com.example.restaurant.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAllIngredient(ModelAndView mav) {
        List<Ingredients> ingredient = ingredientService.printIngredients();
        mav.setViewName("ingredients");
        mav.addObject("ingredientList", ingredient);

        return mav;
    }

    @RequestMapping(value = "/createIngredient", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createIngredientForm(ModelAndView mav) {

        mav.setViewName("createIngredient");

        return mav;
    }
    @RequestMapping(value = "/createIngredient", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView createIngredient(@ModelAttribute("ingredient") Ingredients ingredient, BindingResult result, ModelAndView mav){
        mav.setViewName("/createIngredient");

        ingredientService.create(ingredient);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        return redirectView;
    }

    @RequestMapping(value="/updateIngredient/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateIngredientForm(@PathVariable("id") Long id,ModelAndView mav) {

        mav.setViewName("/updateIngredient");
        Ingredients ingredient = ingredientService.findIngredient(id);
        mav.addObject("ingredient",ingredient);

        return mav;
    }

    @RequestMapping(value="/updateIngredient/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView updateIngredient(@PathVariable("id") Long id,@ModelAttribute("ingredient") Ingredients ingredient, ModelAndView mav){


            Ingredients updatedIngredient = ingredientService.update(ingredient);


        mav.setViewName("/updateIngredient");
        
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        return redirectView;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredients ingredient,ModelAndView mav) {
        ingredientService.deleteIngredient(id);
        mav.setViewName("/ingredients");
        //return mav;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingredients");
        return redirectView;
    }
}
