package ru.netology.moneytranferapp.exception;

public class WrongInputData extends RuntimeException{
    public WrongInputData(String message, String id) {
        super(message);
    }
}
