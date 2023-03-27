package ru.netology.moneytranferapp.exception;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException{
    private final String id;
    public ServerException(String message, String id) {
        super(message + id);
        this.id = id;
    }
}
