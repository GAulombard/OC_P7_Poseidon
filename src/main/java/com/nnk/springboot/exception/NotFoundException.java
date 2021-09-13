package com.nnk.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

    /**
     * Instantiates a new Not found exception.
     *
     * @param s the s
     */
    public NotFoundException(String s) {
        super(s);
    }
}
