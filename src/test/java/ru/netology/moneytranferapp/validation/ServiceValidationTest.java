package ru.netology.moneytranferapp.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.netology.moneytranferapp.exception.ServerException;
import ru.netology.moneytranferapp.exception.WrongInputData;
import ru.netology.moneytranferapp.model.Amount;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

import java.util.stream.Stream;

public class ServiceValidationTest {
    ServiceValidation serviceValidation;

    @BeforeEach
    public void initServiceValidation() {
        serviceValidation = new ServiceValidation();
    }

    @ParameterizedTest
    @MethodSource("nullTransferBetweenCards")
    @DisplayName("Проверка отработке ошибки про операции равной null")
    public void validTransferBetweenCards(TransferBetweenCards transferBetweenCards, String id) {
        ServerException exception = Assertions.assertThrows(ServerException.class, () -> serviceValidation.validTransfer(transferBetweenCards, id));
        String expected = String.format("Ошибка операции: операция не определена %s", id);
        Assertions.assertEquals(exception.getMessage(), expected);

    }

    @ParameterizedTest
    @MethodSource("nullCardToNumber")
    @DisplayName("Проверка отработки ошибки при незаполненной карте получателя")
    public void validCardToNumber(TransferBetweenCards transferBetweenCards, String id) {
        WrongInputData exception = Assertions.assertThrows(WrongInputData.class, () -> serviceValidation.validTransfer(transferBetweenCards, id));
        String expected = String.format("Карта зачисления не заполнена %s", id);
        Assertions.assertEquals(exception.getMessage(), expected);
    }

    @ParameterizedTest
    @MethodSource("nullCardFromNumber")
    @DisplayName("Проверка отработки ошибки при пустой карте отправителя")
    public void validCardFromNumber(TransferBetweenCards transferBetweenCards, String id) {
        WrongInputData exception = Assertions.assertThrows(WrongInputData.class, () -> serviceValidation.validTransfer(transferBetweenCards, id));
        String expected = String.format("Карта списания не заполнена %s", id);
        Assertions.assertEquals(exception.getMessage(), expected);

    }

    @ParameterizedTest
    @MethodSource("nullCardFromCVV")
    @DisplayName("Проверка отработки ошибки при пустом CVV")
    public void validCardCVV(TransferBetweenCards transferBetweenCards, String id) {
        WrongInputData exception = Assertions.assertThrows(WrongInputData.class, () -> serviceValidation.validTransfer(transferBetweenCards, id));
        String expected = String.format("CVV не заполнен %s", id);
        Assertions.assertEquals(exception.getMessage(), expected);
    }
        @ParameterizedTest
        @NullSource
        @DisplayName("Проверка отработки ошибки потверждения операции при null")
        public void validConfirmOperation (OperationConfirmation operationConfirmation){
        ServerException exception = Assertions.assertThrows(ServerException.class, ()-> serviceValidation.validConfirmOperation(operationConfirmation));
        String expected = String.format("Ошибка подтверждения операции %s", null);
        Assertions.assertEquals(exception.getMessage(), expected);

    }

    public static Stream<Arguments> nullTransferBetweenCards() {
        return Stream.of(Arguments.of(null, "10"),
                Arguments.of(null, "0"),
                Arguments.of(null, "2"));
    }

    public static Stream<Arguments> nullCardToNumber() {
        return Stream.of(Arguments.of(new TransferBetweenCards("1111111111111111", "05/26", "111",
                        null, new Amount(100, "rub")), "1"),
                Arguments.of(new TransferBetweenCards("222222222222222222222", "03/27", "222",
                        null, new Amount(300, "rub")), "2"));
    }

    public static Stream<Arguments> nullCardFromNumber() {
        return Stream.of(Arguments.of(new TransferBetweenCards(null, "05/26", "111",
                        "1111111111111111", new Amount(100, "rub")), "1"),
                Arguments.of(new TransferBetweenCards(null, "03/27", "222",
                        "222222222222222", new Amount(300, "rub")), "2"));
    }

    public static Stream<Arguments> nullCardFromCVV() {
        return Stream.of(Arguments.of(new TransferBetweenCards("1234123412341234", "05/26", null,
                        "1111111111111111", new Amount(100, "rub")), "1"),
                Arguments.of(new TransferBetweenCards("2345234523452345", "03/27", null,
                        "222222222222222", new Amount(300, "rub")), "2"));
    }

}
