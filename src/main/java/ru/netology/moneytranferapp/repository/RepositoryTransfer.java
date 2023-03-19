package ru.netology.moneytranferapp.repository;

import ru.netology.moneytranferapp.model.TransferBetweenCards;

public interface RepositoryTransfer {
    String saveOperation(TransferBetweenCards transferBetweenCards);
}
