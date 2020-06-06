package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {
        mav.setViewName("/Orders/orders");
        List<DishDto> dishList = DishService.toDto(dishService.findAll());
        List<OrderDto> orderList = OrderService.toDto(orderService.findAll());

        mav.addObject("orderList", orderList);
        mav.addObject("dishList", dishList);

        return mav;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView createOrderOrAddDish(ModelAndView mav,
                                             @ModelAttribute("dish-id") Long dishId,
                                             @ModelAttribute("count") Long count) {
        //TODO: create new order or add dish to opened order
        return mav;
    }

    @RequestMapping(value = "/admin/orders/{id}", method = RequestMethod.DELETE)
    @Secured(value = {"ROLE_ADMIN"})
    public ModelAndView delete(ModelAndView mav,
                                             @PathVariable("id") Long orderId) {
        //TODO: Delete order
        return mav;
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @Secured(value = {"ROLE_USER"})
    public ModelAndView orders(ModelAndView mav) {
        //TODO: Show order statistics for current user
        return mav;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
    @Secured(value = {"ROLE_USER"})
    public ModelAndView confirmOrder(@PathVariable("id") Long orderId,
                                      ModelAndView mav) {
        //TODO: Change order status to IN_PROGRESS
        return mav;
    }

    @RequestMapping(value = "/orders/{id}/pay", method = RequestMethod.POST)
    @Secured(value = {"ROLE_USER"})
    public ModelAndView payOrder(@PathVariable("id") Long orderId,
                                     @ModelAttribute("payment") int payment,
                                     ModelAndView mav) {
        //TODO: proceed payment for order and change status to DONE if payment > total price of order
        return mav;
    }
}
