package com.ecommerce.exceptions;

public class OrderAlreadyCancelledException extends RuntimeException{
    public OrderAlreadyCancelledException(String message){
        super(message);
    }
}
