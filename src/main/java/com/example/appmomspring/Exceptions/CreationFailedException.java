package com.example.appmomspring.Exceptions;

public class CreationFailedException extends RuntimeException{
    public CreationFailedException(Class<?> clazz){
        super("Error al crear " + clazz.getSimpleName()); //Obtiene el nombre simple de la clase sin el paquete
    }

    public CreationFailedException(Class<?> clazz, String message) {
        super("Error al crear " + clazz.getSimpleName() + ": " + message);
    }
}
