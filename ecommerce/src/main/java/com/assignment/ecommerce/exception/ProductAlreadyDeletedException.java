package com.assignment.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductAlreadyDeletedException extends RuntimeException {

  public ProductAlreadyDeletedException(String message) {
    super(message);
  }
}