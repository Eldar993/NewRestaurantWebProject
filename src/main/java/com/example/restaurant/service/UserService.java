package com.example.restaurant.service;

import com.example.restaurant.entity.User;
import com.example.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    public static final int NAME_LENGTH = 5;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create() {
        User user = new User();
        user.setName(generateRandomString(NAME_LENGTH));
        user.setPassword(generateRandomString(NAME_LENGTH*3));

        return userRepository.saveAndFlush(user);
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateRandomString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public List<User> printUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

}
