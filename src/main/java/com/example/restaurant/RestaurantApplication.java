package com.example.restaurant;

import com.example.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }


    public void run(String... args) throws Exception{
        userService.createRandomUser(10);
        userService.createRandomUser(5);

        userService.printUsers();
    }
}
