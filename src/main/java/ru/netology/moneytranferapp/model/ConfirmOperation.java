package ru.netology.moneytranferapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ConfirmOperation {
private String operationID;
private String code;

public void setOperationID(String operationID){
    this.operationID = operationID;
}

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "operationID='" + operationID + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
