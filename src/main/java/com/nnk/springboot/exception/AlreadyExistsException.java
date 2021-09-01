package com.nnk.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends Throwable {
    public AlreadyExistsException(String s) {
        super(s);
    }
}
