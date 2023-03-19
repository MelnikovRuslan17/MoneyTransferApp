package ru.netology.moneytranferapp.service;

import org.apache.log4j.Logger;
import ru.netology.moneytranferapp.model.ConfirmOperation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.repository.RepositoryImpl;
import ru.netology.moneytranferapp.validation.ServiceValidation;
import org.springframework.stereotype.Service;




@Service

public class ServiceImpl implements ServiceTransfer{

    private static final Logger logger = Logger.getLogger(ServiceImpl.class);
    private final RepositoryImpl repository;
    private final ServiceValidation serviceValidation;
    public ServiceImpl(RepositoryImpl repository, ServiceValidation serviceValidation){
        this.repository = repository;
        this.serviceValidation = serviceValidation;
    }



    public String transfer(TransferBetweenCards betweenCards) {
        String id = repository.saveOperation(betweenCards);
        serviceValidation.validTransfer(betweenCards, id);
        logger.info(String.format("Операция %s на перевод денежных средств вненесена под id %s", betweenCards, id ));
        return id;
    }


    public String confirmOperation(ConfirmOperation confirmOperation) {
        serviceValidation.confirmTransfer(confirmOperation);
        logger.info(String.format("Операция %s подтверждена ", confirmOperation.getOperationID()));
        return confirmOperation.getOperationID();
    }
}
