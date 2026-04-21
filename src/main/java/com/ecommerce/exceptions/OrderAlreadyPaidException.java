package com.ecommerce.exceptions;

public class OrderAlreadyPaidException extends RuntimeException{
    public OrderAlreadyPaidException(String message){
        super(message);
    }
}
