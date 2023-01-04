package com.example.wschat.exceptions;

public class URLException extends RuntimeException {

    public URLException(String message) {
        super(message);
    }

    public URLException(Throwable cause) {
        super(cause);
    }
}
