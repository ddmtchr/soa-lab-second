package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends HttpException {
    public NotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
