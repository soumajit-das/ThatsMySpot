package com.thatsmyspot.commonlib.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus code;
    private Object context;

    public BaseException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(HttpStatus code, String message, Object context) {
        super(message);
        this.code = code;
        this.context = context;
    }
}
