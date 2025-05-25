package com.thatsmyspot.commonlib.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseException {
    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerException(Object context) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", context);
    }

    public InternalServerException(String message, Object context) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, context);
    }
}
