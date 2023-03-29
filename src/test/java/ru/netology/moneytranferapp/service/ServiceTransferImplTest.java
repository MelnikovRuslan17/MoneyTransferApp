package ru.netology.moneytranferapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.moneytranferapp.model.Amount;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.repository.RepositoryTransferImpl;

import java.util.stream.Stream;

public class ServiceTransferImplTest {
    RepositoryTransferImpl repositoryTransfer;


    @BeforeEach
    void init() {
        repositoryTransfer = Mockito.mock(RepositoryTransferImpl.class);
    }
    @MethodSource("transferArguments")
    @ParameterizedTest
    @DisplayName("Проверяем метод transfer на возврат id")
    void transferTest(TransferBetweenCards transferBetweenCards){
        Mockito.when(repositoryTransfer.saveOperation(transferBetweenCards)).thenReturn("1");
        ServiceTransferImpl serviceTransfer = new ServiceTransferImpl(repositoryTransfer);
        String result = serviceTransfer.transfer(transferBetweenCards);
        String expected = "1";
        Assertions.assertEquals(expected,result);

       }
    @MethodSource("confirmOperationMethodArguments")
    @ParameterizedTest
    @DisplayName("Проверяется метод confirmOperation сервиса, принимающий мок репозитория, код операции равен 10")
    void serviceConfirmOperationTest(OperationConfirmation operationConfirmation) {
        ServiceTransferImpl serviceTransfer = new ServiceTransferImpl(repositoryTransfer);
        String result = serviceTransfer.confirmOperation(operationConfirmation);
        String expected = "10";
        Assertions.assertEquals(expected, result);
    }

        public static Stream<Arguments> transferArguments(){
        return Stream.of(Arguments.of(new TransferBetweenCards("1111111111111111", "11/26",
                "111", "2222222222222222", new Amount(333333, "rub"))));
    }
    public static Stream<Arguments> confirmOperationMethodArguments() {
        return Stream.of(Arguments.of(
                new OperationConfirmation("10", "0000")));
    }
}
