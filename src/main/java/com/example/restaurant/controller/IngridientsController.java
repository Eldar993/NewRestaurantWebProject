package com.example.restaurant.controller;

import com.example.restaurant.entity.Ingridients;
import com.example.restaurant.repository.IngridientsRepository;
import com.example.restaurant.service.IngridientsService;
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
    public RedirectView createIngridient(@ModelAttribute("ingridient") Ingridients ingridient, BindingResult result, ModelAndView mav){
        mav.setViewName("/createIngridient");

        ingridientsService.create(ingridient);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingridients");
        return redirectView;
    }

    @RequestMapping(value="/updateIngridient", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateIngridientForm(ModelAndView mav) {

        mav.setViewName("/updateIngridient");

        return mav;
    }

    @RequestMapping(value="/updateIngridient", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView updateIngridient(@ModelAttribute("ingridient") Ingridients ingridient, ModelAndView mav){


            ingridientsService.findIngridientById(ingridient.getId());
            ingridientsService.update(ingridient);


        mav.setViewName("/updateIngridient");

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingridients");
        return redirectView;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteIngridient(@PathVariable("id") Long id, @ModelAttribute("ingridient") Ingridients ingridient,ModelAndView mav) {
        ingridientsService.deleteIngridient(id);
        mav.setViewName("/ingridients");
        //return mav;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/ingridients");
        return redirectView;
    }
}
