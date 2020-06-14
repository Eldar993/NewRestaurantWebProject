package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CookController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/cook/orders", method = RequestMethod.GET)
    @Secured(value = {"ROLE_COOK"})
    public ModelAndView printAll(ModelAndView mav) {
        mav.setViewName("/Orders/orders");
        List<DishDto> dishList = DishService.toDto(dishService.findAll());
        List<OrderDto> orderList = OrderService.toDto(orderService.findAll());

        mav.addObject("orderList", orderList);
        mav.addObject("dishList", dishList);

        return mav;


    }

    /*@RequestMapping(value = "/cook/orders", method = RequestMethod.GET)
    @Secured(value = {"ROLE_COOK"})
    public ModelAndView ordersToPrepare(ModelAndView mav) {
        //TODO: Show orders that should be prepared

        return mav;
    }
*/
    @RequestMapping(value = "/cook/orders/status", method = RequestMethod.PUT)
    @Secured(value = {"ROLE_COOK"})
    public ModelAndView ordersToPrepare(ModelAndView mav) {

        //orderService.confirmOrder();

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/cook/orders");
        mav.setView(redirectView);


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
