package com.eldopay.companyservice.exceptions;

/**
 * @author Samson Effes
 */

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
