package com.example.restaurant.service;

import com.example.restaurant.entity.User;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
   // private final JdbcTemplate jdbcTemplate;

    public static final int NAME_LENGTH = 5;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.jdbcTemplate = jdbcTemplate;
    }

    public boolean create(User user) {

        Optional<User> oldUser = userRepository.findByName(user.getName());
        if (oldUser.isPresent()) {
            return false;
        }
//        user.setPassword(generateRandomString(NAME_LENGTH*3));

       /* jdbcTemplate.update("insert into users(id, name. password) values (:id, :name, :password)",
                123,
                user.getName(),
                user.getPassword()
        );*/
        userRepository.saveAndFlush(user);
        return true;
    }

    public Optional<User> findUser(Long id){
        Optional<User> userInfo = userRepository.findById(id);
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

}
