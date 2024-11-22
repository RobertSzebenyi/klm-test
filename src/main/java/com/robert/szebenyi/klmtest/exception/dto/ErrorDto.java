package com.robert.szebenyi.klmtest.exception.dto;

public class ErrorDto {


    String message;

    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public static ErrorDto create(String message) {
        return new ErrorDto(message);
    }

    public static ErrorDto createFromException(Exception ex) {
        return new ErrorDto(ex.getMessage());
    }

    public String getMessage() {
        return message;
    }

}
