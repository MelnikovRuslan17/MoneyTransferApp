package ru.netology.moneytranferapp.model;


import java.util.Objects;

public class SuccessOK {
    String operationId;

    public SuccessOK(String operationId){
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuccessOK successOK)) return false;

        return getOperationId().equals(successOK.getOperationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperationId());
    }
}
