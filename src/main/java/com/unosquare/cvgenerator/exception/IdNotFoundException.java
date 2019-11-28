package com.unosquare.cvgenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Object of given ID not found")
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }

}
