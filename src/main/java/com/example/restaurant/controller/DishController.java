package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.DishTypeDto;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.DishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DishController {
    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {

        mav.setViewName("/Dishes/dishes");
        List<DishDto> dishList =DishService.toDto(dishService.findAll());

        mav.setViewName("/Dishes/dishes");
        mav.addObject("dishList", dishList);

        return mav;

    }
    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute ModelAndView mav) {

        mav.setViewName("/Dishes/dishForm");
        mav.addObject("dish", new Dish());
        mav.addObject("actionType", "create");
        return mav;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("dish") @Valid DishDto dishDto, BindingResult result, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("/Dishes/dishForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("actionType", "create");
            mav.addObject("dish", dishDto);
        } else {
            Dish entity = dishService.toEntity(dishDto);
            dishService.create(entity);

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/Dishes/dishes");
            mav.setView(redirectView);

        }
        return mav;

    }


}
