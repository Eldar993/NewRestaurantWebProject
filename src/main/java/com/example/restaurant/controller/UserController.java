package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createUser(ModelAndView mav) {
        User createdUser = userService.create();
        mav.setViewName("create");
        mav.addObject("jamesBond", createdUser);

        return mav;
    }
    @RequestMapping(value = "/print_all", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAll(ModelAndView mav) {
        List<User> printUsers = userService.printUsers();
        mav.setViewName("print_all");
        mav.addObject("usersList", printUsers);

        return mav;
    }

    @RequestMapping(value = "/delete_all", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteAll(ModelAndView mav) {
        userService.deleteUsers();
        String msg =  "Successfully deleted all users";
        mav.setViewName("delete_all");
        mav.addObject("deletedUsers",msg);

        return mav;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@ModelAttribute("user") User user,
                        BindingResult result, ModelAndView mav){

        System.out.println("login: " + user.getName());
        System.out.println("password: " + user.getPassword());

        mav.setViewName("/login");

        return mav;
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView loginForm(ModelAndView mav){

        mav.setViewName("/login");

        return mav;

    }

}
