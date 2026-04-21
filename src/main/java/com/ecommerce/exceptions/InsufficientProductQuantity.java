package com.ecommerce.exceptions;

public class InsufficientProductQuantity extends RuntimeException{
    public InsufficientProductQuantity(String message){
        super(message);
    }
}
