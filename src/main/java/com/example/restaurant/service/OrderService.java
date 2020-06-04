package com.example.restaurant.service;

import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    public final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean create(Order order) {
        if (order.getId() != null) {
            return false;
        }
        orderRepository.saveAndFlush(order);
        return true;
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
        if (updatedOrder == null) {
            return null;
        }
        updatedOrder.get().setId(order.getId());
        updatedOrder.get().setCreatedAt(order.getCreatedAt());
        updatedOrder.get().setOrderStatus(order.getOrderStatus());
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
        result.setOrderStatus(entity.getOrderStatus());

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
        result.setOrderStatus(dto.getOrderStatus());
        result.setUser(dto.getUser());
        return result;
    }
}
