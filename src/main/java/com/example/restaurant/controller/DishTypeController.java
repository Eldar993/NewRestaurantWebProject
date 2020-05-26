package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.service.DishTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@Controller
public class DishTypeController {
    @Autowired
    private DishTypeService dishTypeService;

    @RequestMapping(value = "/dishTypes", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAll(ModelAndView mav) {
        List<DishType> dishTypeList = dishTypeService.findAll();
        dishTypeService.toDto(dishTypeList);
        mav.setViewName("/DishTypes/dishTypes");
        mav.addObject("dishTypeList",dishTypeList);

        return mav;
    }

    @RequestMapping(value = "/dishType", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createForm(@ModelAttribute ModelAndView mav) {

        mav.setViewName("/DishTypes/dishTypeForm");
        mav.addObject("dishType", new DishType());
        mav.addObject("actionType", "create");
        return mav;
    }


}
