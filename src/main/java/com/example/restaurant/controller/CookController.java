package com.example.restaurant.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CookController {

    @RequestMapping(value = "/cook/orders", method = RequestMethod.GET)
    @Secured(value = {"ROLE_COOK"})
    public ModelAndView ordersToPrepare(ModelAndView mav) {
        //TODO: Show orders that should be prepared
        return mav;
    }

    @RequestMapping(value = "/cook/orders/{id}", method = RequestMethod.POST)
    @Secured(value = {"ROLE_COOK"})
    public ModelAndView completeOrder(@PathVariable("id") Long orderId,
                                      ModelAndView mav) {
        //TODO: Change order status to WAIT_PAYMENT
        return mav;
    }
}
