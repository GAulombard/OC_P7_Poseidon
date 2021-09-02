package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.AlreadyExistsException;
import com.nnk.springboot.exception.NotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        LOGGER.info("Process to find all user");

        return userRepository.findAll();
    }

    public void save(User user) throws AlreadyExistsException {
        LOGGER.info("Process to save new user");

        if(userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

    }

    public void update(User user) throws NotFoundException {
        LOGGER.info("Process to save new user");

        if(!userRepository.existsByUsername(user.getUsername())) {
            throw new NotFoundException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

    }

    public User findById(Integer id) throws NotFoundException {
        LOGGER.info("Process to find user by id");

        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        return userRepository.getOne(id);
    }

    public void deleteById(Integer id) throws NotFoundException {
        LOGGER.info("Process to delete user by id");

        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(id);

    }
}
