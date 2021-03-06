package com.example.restaurant.controller;

import com.example.restaurant.dto.DishTypeDto;

import com.example.restaurant.entity.DishType;

import com.example.restaurant.service.DishTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;


@Controller
public class DishTypeController {
    @Autowired
    private DishTypeService dishTypeService;

    @RequestMapping(value = "/dishTypes", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {
        List<DishTypeDto> dishTypeList = DishTypeService.toDto(dishTypeService.findAll());
        mav.setViewName("/DishTypes/dishTypes");
        mav.addObject("dishTypeList", dishTypeList);

        return mav;
    }


    @RequestMapping(value = "/dishType", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute ModelAndView mav) {

        mav.setViewName("/DishTypes/dishTypeForm");
        mav.addObject("dishType", new DishType());
        mav.addObject("actionType", "create");
        return mav;
    }

    @RequestMapping(value = "/dishType", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("dishType") @Valid DishTypeDto dishTypeDto, BindingResult result, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("/DishTypes/dishTypeForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("actionType", "create");
            mav.addObject("dishType", dishTypeDto);
        } else {
            DishType entity = DishTypeService.toEntity(dishTypeDto);
            dishTypeService.create(entity);

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/dishTypes");
            mav.setView(redirectView);

        }
        return mav;

    }

    @RequestMapping(value = "/dishType/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Long id, ModelAndView mav) {

        mav.setViewName("/DishTypes/dishTypeForm");
        DishTypeDto dishType = DishTypeService.toDto(dishTypeService.findById(id));
        mav.addObject("dishType", dishType);

        return mav;
    }

    @RequestMapping(value = "/dishType/{id}", method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("dishType") @Valid DishTypeDto dishTypeDto, BindingResult result, ModelAndView mav) {

        if (result.hasErrors()) {
            mav.setViewName("/DishTypes/dishTypeForm");
            for (FieldError fieldError : result.getFieldErrors()) {
                mav.addObject(fieldError.getField() + "_hasError", true);
            }
            mav.addObject("dishType", dishTypeDto);
        } else {
            DishType entity = DishTypeService.toEntity(dishTypeDto);
            dishTypeService.update(entity);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/dishTypes");
            mav.setView(redirectView);
        }
        return mav;

    }

    @RequestMapping(value = "/dishType/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") Long id) {
        dishTypeService.deleteById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/dishTypes");
        return redirectView;
    }
   /* @RequestMapping(value = "/delete_all_dishTypes", method = RequestMethod.DELETE)
    @ResponseBody
    public ModelAndView deleteAll(ModelAndView mav) {
        dishTypeService.deleteAll();
        String msg = "Successfully deleted all dish types";
        mav.setViewName("delete_all");
        mav.addObject("deleted", msg);

        return mav;
    }*/

}
