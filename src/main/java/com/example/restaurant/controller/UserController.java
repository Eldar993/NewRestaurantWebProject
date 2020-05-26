package com.example.restaurant.controller;

import com.example.restaurant.entity.User;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /* @RequestMapping(value = "/create", method = RequestMethod.GET)
     public ModelAndView createUser(ModelAndView mav) {
         User createdUser = userService.create();
         mav.setViewName("create");
         mav.addObject("jamesBond", createdUser);

         return mav;
     }*/
    @RequestMapping(value = "/print_all", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView printAll(ModelAndView mav, @ModelAttribute("user") User user) {
        List<User> printUsers = userService.printUsers();

        mav.setViewName("print_all");
        mav.addObject("usersList", printUsers);

        mav.addObject("userInfo", user);
        return mav;
    }

    @RequestMapping(value = "/delete_all", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteAll(ModelAndView mav) {
        userService.deleteUsers();
        String msg = "Successfully deleted all users";
        mav.setViewName("delete_all");
        mav.addObject("deleted", msg);

        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@ModelAttribute("user") User user,
                              BindingResult result, ModelAndView mav) {



        mav.setViewName("/main");
        userService.toDto(user);
        userService.create(user);
        return mav;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView change(@PathVariable("id") Long id,ModelAndView mav) {
        ///// find user by id and return user's data
        Optional<User> user = userService.findUser(id);

        mav.setViewName("/user_profile");
        //Optional<AnyType>: "Optional[" + anyType.toString() + "]"
        mav.addObject("userInfo",user.orElse(null));
        //mav.addObject("isEven",userService.isEven(id));


        return mav;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ModelAndView change(@PathVariable("id") Long id, @ModelAttribute("user") User user,ModelAndView mav) {
        if(id.equals(user.getId())){
            userService.findUser(id);
            userService.update(user);
        }
        mav.setViewName("/user_profile");
        userService.toDto(user);
        mav.addObject("userInfo", user);
        // mav.addObject("newUserName",user.getName());
        // mav.addObject("newUserPassword",user.getPassword());

        // change user's data (find by id, if user was found then update information)
        return mav;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm(ModelAndView mav) {

        mav.setViewName("/login");

        return mav;

    }

}
