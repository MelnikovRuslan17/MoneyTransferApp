package ru.netology.moneytranferapp.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository

public class RepositoryTransferImpl implements RepositoryTransfer {
    private final Map<Integer, TransferBetweenCards> storageOperation = new ConcurrentHashMap<>();
    private AtomicInteger id= new AtomicInteger(0);
    private static final Logger logger = Logger.getLogger(RepositoryTransferImpl.class);
//

    @Override
    public String saveOperation(TransferBetweenCards transferBetweenCards) {
        storageOperation.putIfAbsent(id.get(), transferBetweenCards);
        logger.info("Транзакция сохранена в репозиторий " + transferBetweenCards + "ID тарнзакции " + id.get());
        return String.valueOf(id.getAndIncrement());
    }
}
