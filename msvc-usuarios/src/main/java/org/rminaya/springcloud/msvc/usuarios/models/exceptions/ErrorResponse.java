package org.rminaya.springcloud.msvc.usuarios.models.exceptions;

public class ErrorResponse extends BaseErrorResponse {
    private String error;

    public ErrorResponse(String status, Integer code, String error) {
        super(status, code);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
