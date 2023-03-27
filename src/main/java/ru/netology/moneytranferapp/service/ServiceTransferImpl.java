package ru.netology.moneytranferapp.service;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.repository.RepositoryTransferImpl;
import ru.netology.moneytranferapp.validation.ServiceValidation;


@Service
@RequiredArgsConstructor

public class ServiceTransferImpl implements ServiceTransfer {

    private static final Logger logger = Logger.getLogger(ServiceTransferImpl.class);
    private final RepositoryTransferImpl repository;
    private final ServiceValidation serviceValidation = new ServiceValidation();

    public String transfer(TransferBetweenCards transferBetweenCards) {
        String id = repository.saveOperation(transferBetweenCards);
        serviceValidation.validTransfer(transferBetweenCards, id);
        logger.info(String.format("Транзакция %s сохранена под id %s ", transferBetweenCards, id));
        return id;
    }

    @Override
    public String confirmOperation(OperationConfirmation operationConfirmation) {
        serviceValidation.validConfirmOperation(operationConfirmation);
        logger.info(String.format("Операция с id: %s подтверждена. Перевод выполнен успешно", operationConfirmation.getOperationId()));
        return operationConfirmation.getOperationId();
    }
}
