package com.restaurant.system.model.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class entity, String cause) {
        super("Entity for class " + entity + " and " + cause + " not found");
    }
}
