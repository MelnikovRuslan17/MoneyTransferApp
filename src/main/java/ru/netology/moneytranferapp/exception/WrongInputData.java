package ru.netology.moneytranferapp.exception;

import lombok.Getter;

@Getter
public class WrongInputData extends RuntimeException{
    private final String id;
    public WrongInputData(String message, String id) {
        super(message + id);
        this.id = id;
    }
}
