package com.restaurant.system.model.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Class entity, String cause) {
        super("Entity for class " + entity + " and " + cause + " already exists");
    }
}
