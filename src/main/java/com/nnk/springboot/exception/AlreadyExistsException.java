package com.nnk.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Already exists exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends Exception {
    /**
     * Instantiates a new Already exists exception.
     *
     * @param s the s
     */
    public AlreadyExistsException(String s) {
        super(s);
    }
}
