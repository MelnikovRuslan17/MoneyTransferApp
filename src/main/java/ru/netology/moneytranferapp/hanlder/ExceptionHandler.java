package ru.netology.moneytranferapp.hanlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytranferapp.exception.ServerException;
import ru.netology.moneytranferapp.exception.WrongInputData;


@RestControllerAdvice

public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(WrongInputData.class)

    public ResponseEntity<String>wrongInputDataHandler(WrongInputData e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ServerException.class)
    public ResponseEntity<String>serverExceptionHandler(ServerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
