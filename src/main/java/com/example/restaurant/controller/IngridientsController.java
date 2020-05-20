package com.example.restaurant.controller;

import com.example.restaurant.entity.Ingridients;
import com.example.restaurant.service.IngridientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IngridientsController {
    @Autowired
    private IngridientsService ingridientsService;

    @RequestMapping(value = "/ingridients", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAllIngridients(ModelAndView mav) {
        List<Ingridients> ingridients = ingridientsService.printIngridients();
        mav.setViewName("ingridients");
        mav.addObject("IngridientsList", ingridients);

        return mav;
    }

    @RequestMapping(value = "/createIngridient", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createIngridientForm(ModelAndView mav) {

        mav.setViewName("createIngridient");

        return mav;
    }
    @RequestMapping(value = "/createIngridient", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createIngridient(@ModelAttribute("ingridient") Ingridients ingridient, BindingResult result,ModelAndView mav){
        mav.setViewName("/createIngridient");

        ingridientsService.create(ingridient);
        return mav;
    }


}
