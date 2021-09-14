package com.nnk.springboot.annotation;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The type Unique validator.
 */
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        Boolean result = true;


        //test if a user with the same full name or username already exists in the database
        if(userRepository.existsByUsername(s) || userRepository.existsByFullName(s)) {
            result = false;
        }

        return result;
    }
}
