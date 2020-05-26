package com.example.restaurant.controller;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import com.example.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DishController {
    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAll(ModelAndView mav) {

        mav.setViewName("/Dishes/dishes");
        List<Dish> dishList = dishService.findAll();
        dishService.toDto(dishList);
        mav.setViewName("/Dishes/dishes");
        mav.addObject("dishTypeList", dishList);

        return mav;

    }
    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createForm(@ModelAttribute ModelAndView mav) {

        mav.setViewName("/Dishes/dishForm");
        mav.addObject("dish", new Dish());
        mav.addObject("actionType", "create");
        return mav;
    }


}
