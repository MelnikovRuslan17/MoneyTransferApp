package ru.netology.moneytranferapp.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository

public class RepositoryImpl implements RepositoryTransfer {
    private final Map<Long, TransferBetweenCards> storageOperation = new ConcurrentHashMap<>();
    private AtomicLong atomicID = new AtomicLong(0);
    private static final Logger logger = Logger.getLogger(RepositoryImpl.class);

    @Override
    public String saveOperation(TransferBetweenCards transferBetweenCards) {

       storageOperation.put(atomicID.get(),transferBetweenCards);
       logger.info("Трансфер сохранен " + transferBetweenCards + " под ID " + atomicID.get());
       return String.valueOf(atomicID.getAndIncrement());
    }
}
