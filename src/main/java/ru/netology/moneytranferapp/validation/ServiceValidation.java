package ru.netology.moneytranferapp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.netology.moneytranferapp.exception.ServerException;
import ru.netology.moneytranferapp.exception.WrongInputData;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

@Component

@Getter
@AllArgsConstructor

public class ServiceValidation {
    private static final Logger logger = Logger.getLogger(ServiceValidation.class);
    //  private ServiceImpl service;
    private static final String codeConformation = "0000";

    public void validTransfer(TransferBetweenCards transferBetweenCards, String id) {

            if (transferBetweenCards == null) {
                logger.error("Ошибка операции: операция не определена");
                throw new ServerException("Ошибка операции: операция не определена ", id);
            }
            if (transferBetweenCards.getCardToNumber() == null) {
                logger.error("Карта зачисления не заполнена ");
                throw new WrongInputData("Карта зачисления не заполнена ", id);
            }
            if (transferBetweenCards.getCardFromNumber() == null) {
                logger.error("Карта списания не заполнена");
                throw new WrongInputData("Карта списания не заполнена ", id);
            }
            if (transferBetweenCards.getCardFromCVV() == null) {
                logger.error("CVV не заполнен");
                throw new WrongInputData("CVV не заполнен ", id);
            }
            if (transferBetweenCards.getCardFromValidTill() == null) {
                logger.error("Указана неверная дата карты списания");
                throw new WrongInputData("Указана неверная дата карты списания ", id);
            }
            if (transferBetweenCards.getAmount().getValue() == 0) {
               logger.error("Не указана сумма списания");
                throw new WrongInputData("Не указана сумма списания ", id);
            }
        }
    public void validConfirmOperation(OperationConfirmation operationConfirmation) {
        if (operationConfirmation == null) {
            logger.info("Ошибка подтверждения операции");
            throw new ServerException("Ошибка подтверждения операции ", null);
        }
        String id = operationConfirmation.getOperationId();
        if (id == null) {
           logger.error("Неправльный id операции");
            throw new WrongInputData("Неправльный id операции ", null);
        }
        if (!(operationConfirmation.getCode().equals(codeConformation))) {
            logger.error("Неправльный код подтвержедния операции");
            throw new WrongInputData("Неправльный код подтвержедния операции ", id);
        }
    }
}
