package com.example.homework36.exception;

public class StudentNotFoundException extends RuntimeException{

    private final long id;

    public StudentNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
