package ru.netology.moneytranferapp.exception;

public class ServerException extends RuntimeException{
    public ServerException(String message, String id) {
        super(message);
    }
}
