package com.example.appmomspring.Exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Integer eventId) {
        super("El evento con id: " + eventId + " no fue encontrado");
    }
}
