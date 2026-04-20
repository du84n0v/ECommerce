package com.ecommerce.exceptions;

public class NotFoundOrdersException extends RuntimeException{
    public NotFoundOrdersException(String message){
        super(message);
    }
}
