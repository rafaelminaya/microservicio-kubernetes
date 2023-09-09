package org.rminaya.springcloud.msvc.usuarios.models.exceptions;

public class EmailFoundException extends RuntimeException {
    // Constructor
    public EmailFoundException() {
        super("Ya existe un usuario con ese email.");
    }
}
