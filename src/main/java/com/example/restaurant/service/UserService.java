package com.example.restaurant.service;

import com.example.restaurant.dto.UserDto;
import com.example.restaurant.entity.User;
import com.example.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    public static final int NAME_LENGTH = 5;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public boolean create(User user) {

        Optional<User> oldUser = userRepository.findByName(user.getName());
        if (oldUser.isPresent()) {
            return false;
        }
        userRepository.saveAndFlush(user);
        return true;
    }

    public Optional<User> findUser(Long id) {
        Optional<User> userInfo = userRepository.findUserById(id);
        return userInfo;
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

    public boolean update(User user) {
        Optional<User> oldInfo = userRepository.findById(user.getId());
        userRepository.saveAndFlush(user);
        return true;
    }

    public List<User> printUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setPassword(user.getPassword());
        result.setUserRole(user.getUserRole());

        return result;
    }

    public static List<UserDto> toDto(List<User> users) {
        return users
                .stream()
                .map(UserService::toDto)
                .collect(Collectors.toList());
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User result = new User();
        result.setId(dto.getId());
        result.setName(dto.getName());
        result.setPassword(dto.getPassword());
        result.setUserRole(dto.getUserRole());
        return result;
    }

    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }
}
