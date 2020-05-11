package com.example.restaurant.controller;

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

    @RequestMapping(value = "http://localhost:8080/create", method = RequestMethod.POST)
    @ResponseBody
    public void createUser() {
        userService.create("Tom");
    }
    @RequestMapping(value = "http://localhost:8080/print_all", method = RequestMethod.GET)
    @ResponseBody
    public void printAll() {
        userService.printUsers();
    }

    @RequestMapping(value = "http://localhost:8080/delete_all", method = RequestMethod.GET)
    @ResponseBody
    public void deleteAll() {
        userService.deleteUsers();;
    }

}
