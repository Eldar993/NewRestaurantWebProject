package com.example.restaurant.controller;

import com.example.restaurant.dto.UserDto;
import com.example.restaurant.entity.User;
import com.example.restaurant.enums.UserRoles;
import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/print_all", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav, @ModelAttribute("user") UserDto userDto) {
        List<UserDto> printUsers = UserService.toDto(userService.printUsers());

        mav.setViewName("print_all");
        mav.addObject("usersList", printUsers);

        mav.addObject("userInfo", userDto);
        return mav;
    }

    @RequestMapping(value = "/delete_all", method = RequestMethod.GET)
    public ModelAndView deleteAll(ModelAndView mav) {
        userService.deleteUsers();
        String msg = "Successfully deleted all users";
        mav.setViewName("delete_all");
        mav.addObject("deleted", msg);

        return mav;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationForm(ModelAndView mav) {

        mav.setViewName("registration");
        mav.addObject("userInfo", new UserDto());
        return mav;

    }

    @RequestMapping(value = "/qwe")
    @ResponseBody
    public String test() {
        return "Hello world)";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView register(@Validated @ModelAttribute("userInfo") UserDto userDto,
                                 BindingResult bindingResult, ModelAndView mav) {


        if (bindingResult.hasErrors()) {
            mav.setViewName("/registration");
            mav.addObject("userInfo", userDto);
        } else {
            try {
                User entity = UserService.toEntity(userDto);
                final boolean result = userService.create(entity);
                if (result) {
                    RedirectView redirectView = new RedirectView("/");
                    mav.setView(redirectView);
                } else {
                    mav.setViewName("/registration");
                    mav.addObject("userInfo", userDto);
                    mav.addObject("generalError", "Something way wrong:(");
                }
            } catch (Exception e) {
                mav.setViewName("/registration");
                mav.addObject("userInfo", userDto);
                System.out.println(e.getMessage());
                mav.addObject("generalError", "User constraint violation");
            }
        }


        return mav;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView change(@PathVariable("id") Long id, ModelAndView mav) {
        ///// find user by id and return user's data
        Optional<User> user = userService.findUser(id);

        mav.setViewName("/user_profile");
        UserRoles roles[] = UserRoles.values();
        //Optional<AnyType>: "Optional[" + anyType.toString() + "]"
        mav.addObject("userInfo", user.orElse(null));
        mav.addObject("roles", roles);
        //mav.addObject("isEven",userService.isEven(id));


        return mav;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ModelAndView change(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto, ModelAndView mav) {
        User entity = UserService.toEntity(userDto);
        UserRoles roles[] = UserRoles.values();
        mav.addObject("roles", roles);
        if (id.equals(userDto.getId())) {
            userService.findUser(id);
            userService.update(entity);
        }
        mav.setViewName("/user_profile");

        mav.addObject("userInfo", userDto);
        return mav;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") Long id) {
        userService.delete(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/print_all");
        return redirectView;
    }

}
