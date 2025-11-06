package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ConflictException extends HttpException {

    public ConflictException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public ConflictException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
