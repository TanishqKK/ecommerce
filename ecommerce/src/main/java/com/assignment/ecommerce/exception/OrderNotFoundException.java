package com.assignment.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    private boolean alreadyDeleted = false;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, boolean alreadyDeleted) {
        super(message);
        this.alreadyDeleted = alreadyDeleted;
    }

    public boolean isAlreadyDeleted() {
        return alreadyDeleted;
    }
}