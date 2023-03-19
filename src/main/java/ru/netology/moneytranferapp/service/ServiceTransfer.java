package ru.netology.moneytranferapp.service;

import ru.netology.moneytranferapp.model.ConfirmOperation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

public interface ServiceTransfer {
    String transfer(TransferBetweenCards betweenCards);
    String confirmOperation(ConfirmOperation confirmOperation);
}
