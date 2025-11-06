package com.ddmtchr.soalabsecond.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class HttpException extends RuntimeException {

    @Setter
    protected List<String> messages;

    protected HttpStatus httpStatus;

    public HttpException(String message) {
        super(message);
    }

    public HttpException(List<String> messages) {
        this.messages = messages;
    }
}
