package com.thatsmyspot.commonlib.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Not Found");
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Object context) {
        super(HttpStatus.NOT_FOUND, message, context);
    }
}
