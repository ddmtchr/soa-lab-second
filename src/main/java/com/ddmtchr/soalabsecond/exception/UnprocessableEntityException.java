package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class UnprocessableEntityException extends HttpException {
    public UnprocessableEntityException(String message) {
        super(message);
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public UnprocessableEntityException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
