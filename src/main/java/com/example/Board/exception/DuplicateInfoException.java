package com.example.Board.exception;

public class DuplicateInfoException extends RuntimeException {
    public DuplicateInfoException(String message) {
        super(message);
    }
}