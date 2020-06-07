package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

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
                                             Authentication authentication,
                                             @ModelAttribute("dish-id") Long dishId,
                                             @ModelAttribute("count") Long count) {
        //create new order or add dish to opened order
        final String username = authentication.getName();
        try {
            orderService.addDish(username, dishId, count);


        } catch (Exception e) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/error"); //todo: should be redirected to error page
            mav.setView(redirectView);
        }
        //TODO: add redirect to page with all available dishes
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/dishes"); //redirect to dish page
        mav.setView(redirectView);
        return mav;
    }

    @RequestMapping(value = "/admin/orders/{id}", method = RequestMethod.DELETE)
    @Secured(value = {"ROLE_ADMIN"})
    public ModelAndView delete(ModelAndView mav,
                                             @PathVariable("id") Long orderId) {
        // Delete order
        orderService.remove(orderId);
        //TODO: add redirect(?)
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/admin/orders"); //redirect to dish page
        mav.setView(redirectView);
        return mav;
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @Secured(value = {"ROLE_USER"})
    public ModelAndView userOrdersHistory(Authentication authentication,
                               ModelAndView mav) {
        //Show order statistics for current user
        //      orders history
        final String username = authentication.getName();
        final List<OrderDto> orders = orderService.findAllByUser(username)
                .stream()
                .map(OrderService::toDto)
                .collect(Collectors.toList());
        //TODO: pass orders to view
        mav.setViewName("/basket");
        mav.addObject("orders",orders);
        return mav;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
    @Secured(value = {"ROLE_USER"})
    public ModelAndView confirmOrder(@PathVariable("id") Long orderId,
                                     Authentication authentication,
                                      ModelAndView mav) {
        //TODO: Change order status to IN_PROGRESS
        final String username = authentication.getName();
        orderService.confirm(username, orderId);
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
