package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class InternalServerErrorException extends HttpException {
    public InternalServerErrorException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public InternalServerErrorException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
