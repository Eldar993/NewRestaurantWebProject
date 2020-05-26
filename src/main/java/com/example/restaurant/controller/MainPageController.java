package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
    @RequestMapping(value = "/main", method =  RequestMethod.GET)
    @ResponseBody
        public ModelAndView mainPage(ModelAndView mav){
            mav.setViewName("/main");
            return mav;
        }


}
