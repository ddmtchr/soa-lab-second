package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends HttpException {
    public BadRequestException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
