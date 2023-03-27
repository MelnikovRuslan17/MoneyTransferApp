package ru.netology.moneytranferapp.service;

import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

public interface ServiceTransfer {
    String transfer(TransferBetweenCards transferBetweenCards);
    String confirmOperation(OperationConfirmation operationConfirmation);
}
