package ru.netology.moneytranferapp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.netology.moneytranferapp.exception.ServerException;
import ru.netology.moneytranferapp.exception.WrongInputData;
import ru.netology.moneytranferapp.model.ConfirmOperation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
@Component

@Getter
@AllArgsConstructor

public class ServiceValidation {
    private static final Logger logger = Logger.getLogger(ServiceValidation.class);
    private static final String codeConformation = "0000";

    public void validTransfer(TransferBetweenCards transferBetweenCards, String id){
        if(transferBetweenCards.getCardToNumber() == null){
            logger.error("Номер карты получателя не указан");
            throw new WrongInputData("Номер карты получателя не указан", id);

        }
        if(transferBetweenCards.getCardFromNumber() == null){
            logger.error("Номер карты отправлителя не указан");
            throw new WrongInputData("Номер карты отправлителя не указан", id);

        }
        if(transferBetweenCards.getCardFromCVV() == null){
            logger.error("CVV не заполнен");
            throw new WrongInputData("CVV не заполнен", id);
        }
        if(transferBetweenCards.getCardFromValidTill() == null){
            logger.error("Срок действия карты указан не верно");
            throw new WrongInputData("Срок действия карты указан не верно", id);

        }
        if(transferBetweenCards == null){
            logger.error("Операция не определена");
            throw new ServerException("Операция не определена", id);
        }
        if(transferBetweenCards.getAmount().getValue() == 0){
            logger.error("Недостаточно денег на карте");
            throw new WrongInputData("Недостаточно денег на карте", id);
        }
    }
    public void confirmTransfer(ConfirmOperation confirmOperation){
        if(confirmOperation == null){
            logger.error("Ошибка подтверждения операции");
            throw new ServerException("Ошибка подтверждения операции", null);
        }
        if(confirmOperation.getOperationID() == null){
            logger.error("Неверный ID операции");
            throw new WrongInputData("Неверный ID операции", null);
        }
        String id = confirmOperation.getOperationID();
        if(!(confirmOperation.getCode().equals(codeConformation))){
            logger.error("Неверный код подтверждения " + confirmOperation.getCode());
            throw new WrongInputData("Неверный код подтверждения ", id);
        }
    }
}
