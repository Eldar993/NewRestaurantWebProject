package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public void createUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        userService.create("Tom");
    }
    @RequestMapping(value = "/print_all", method = RequestMethod.GET)
    @ResponseBody
    public void printAll() {
        userService.printUsers();
    }

    @RequestMapping(value = "/delete_all", method = RequestMethod.GET)
    @ResponseBody
    public void deleteAll() {
        userService.deleteUsers();;
    }

}
