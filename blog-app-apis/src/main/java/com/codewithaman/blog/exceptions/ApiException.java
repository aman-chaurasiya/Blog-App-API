package com.codewithaman.blog.exceptions;

public class ApiException extends Throwable {
    public ApiException(String message) {
        super(message);
    }
}
