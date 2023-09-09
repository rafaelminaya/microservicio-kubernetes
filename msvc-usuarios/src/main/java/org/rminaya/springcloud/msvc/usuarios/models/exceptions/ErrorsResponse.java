package org.rminaya.springcloud.msvc.usuarios.models.exceptions;

import java.util.List;


public class ErrorsResponse extends BaseErrorResponse {
    private List<String> errors;

    public ErrorsResponse(String status, Integer code, List<String> errors) {
        super(status, code);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
