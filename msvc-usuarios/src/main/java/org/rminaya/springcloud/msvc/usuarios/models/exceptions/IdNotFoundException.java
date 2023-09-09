package org.rminaya.springcloud.msvc.usuarios.models.exceptions;

public class IdNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "El registro no se encuentra en %s";

    // Constructor
    public IdNotFoundException(String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
