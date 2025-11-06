package com.ddmtchr.soalabsecond.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceUnavailableException extends HttpException {
    public ServiceUnavailableException(String message) {
        super(message);
        this.httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
    }

    public ServiceUnavailableException(List<String> messages) {
        super(messages);
        this.httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
    }
}
