package com.example.restaurant.controller;

import com.example.restaurant.dto.DishDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.service.DishService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView printAll(ModelAndView mav) {
        mav.setViewName("/Orders/orders");
        List<DishDto> dishList = DishService.toDto(dishService.findAll());
        List<OrderDto> orderList = OrderService.toDto(orderService.findAll());

        mav.addObject("orderList", orderList);
        mav.addObject("dishList",dishList);

        return mav;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView create(ModelAndView mav, DishDto dishDto, OrderDto orderDto) {

        mav.setViewName("/Orders/orders");
        /*Order order = orderService.toEntity(orderDto);
        Dish entity = dishService.toEntity(dishDto);


        orderService.create(order);

        mav.addObject("order", order);*/
        return mav;
    }


}
