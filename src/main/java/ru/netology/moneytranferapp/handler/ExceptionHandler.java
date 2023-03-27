package ru.netology.moneytranferapp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytranferapp.exception.ServerException;
import ru.netology.moneytranferapp.exception.WrongInputData;
import ru.netology.moneytranferapp.model.ErrorTransfer;
@RestControllerAdvice
public class ExceptionHandler {


        @org.springframework.web.bind.annotation.ExceptionHandler(ServerException.class)
        public ResponseEntity<ErrorTransfer> transferExceptionHandler(ServerException e) {
            return new ResponseEntity<>(new ErrorTransfer(e.getMessage(), e.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(WrongInputData.class)
        public ResponseEntity<ErrorTransfer> wrongInputExceptionHandler(WrongInputData e) {
            return new ResponseEntity<>(new ErrorTransfer(e.getMessage(), e.getId()), HttpStatus.BAD_REQUEST);
        }
}
