package com.nnk.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Forbidden exception.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends Exception {
    /**
     * Instantiates a new Forbidden exception.
     *
     * @param s the s
     */
    public ForbiddenException(String s) {
        super(s);
    }
}
