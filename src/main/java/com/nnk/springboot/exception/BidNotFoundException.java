package com.nnk.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BidNotFoundException extends Exception{

    public BidNotFoundException(String s) {
        super(s);
    }
}
