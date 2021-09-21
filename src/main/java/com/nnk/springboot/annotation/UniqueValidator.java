package com.nnk.springboot.annotation;


import com.nnk.springboot.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * The type Unique validator.
 */
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private static Logger LOGGER = LoggerFactory.getLogger(UniqueValidator.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        if(!userRepository.existsByUsername(s)) {
            LOGGER.info("username doesn't exists");
            return true;
        } else {
            LOGGER.info("username already exists");
            return false;
        }

    }
}
