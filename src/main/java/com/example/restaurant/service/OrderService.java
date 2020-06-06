package com.example.restaurant.service;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import com.example.restaurant.enums.OrderStatus;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishService dishService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, DishService dishService, UserService userService) {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
        this.userService = userService;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Order order = orderRepository.findOrderById(id);
        return order;
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order update(Order order) {
        Optional<Order> updatedOrder = orderRepository.findById(order.getId());
        if (updatedOrder.isEmpty()) {
            return null;
        }
        updatedOrder.get().setId(order.getId());
        updatedOrder.get().setCreatedAt(order.getCreatedAt());
        updatedOrder.get().setStatus(order.getStatus());
        updatedOrder.get().setUser(order.getUser());

        final Order result = orderRepository.saveAndFlush(updatedOrder.get());
        return result;
    }

    public static OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto result = new OrderDto();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setUser(entity.getUser());
        result.setOrderStatus(entity.getStatus());

        return result;
    }

    public static List<OrderDto> toDto(List<Order> orders) {
        return orders
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    public static Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order result = new Order();
        result.setId(dto.getId());
        result.setCreatedAt(dto.getCreatedAt());
        result.setStatus(dto.getOrderStatus());
        result.setUser(dto.getUser());
        return result;
    }

    public void addDish(String username, Long dishId, Long count) {
        final User user = userService.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("User with [name='" + username + "' not found"));
        final Dish dish = dishService.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish with [id='" + dishId + "' not found"));

        final Order order = orderRepository.findFirstByUserAndStatus(user, OrderStatus.NEW)
                .orElseGet(() -> create(user));

        order.addDish(dish, count);
    }

    public Order create(User user) {
        final Long count = orderRepository.countAllByUserAndStatusNot(user, OrderStatus.DONE);
        if (count > 0) {
            throw new IllegalStateException("User has uncompleted order, order in progress or some unpaid order");
        }
        final Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        return orderRepository.saveAndFlush(order);
    }
}
