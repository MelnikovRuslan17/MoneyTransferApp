package ru.netology.moneytranferapp.model;

public class ErrorTransfer{
    String message;
    String operationId;

    public ErrorTransfer(String message, String operationId){
        this.message = message;
        this.operationId = operationId;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
