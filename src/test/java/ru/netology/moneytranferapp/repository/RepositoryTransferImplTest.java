package ru.netology.moneytranferapp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.moneytranferapp.model.Amount;
import ru.netology.moneytranferapp.model.TransferBetweenCards;

import java.util.stream.Stream;

public class RepositoryTransferImplTest {
    RepositoryTransferImpl repositoryTransfer;

    @BeforeEach
    public void initRepository() {
        repositoryTransfer = new RepositoryTransferImpl();
    }

    @ParameterizedTest
    @MethodSource("operationArguments")
    @DisplayName("Проверка сохранения операции")
    public void saveOperationTest(TransferBetweenCards transferBetweenCards){
        String result = repositoryTransfer.saveOperation(transferBetweenCards);
        String expected = "0";
        Assertions.assertEquals(result, expected );
    }

    private static Stream<Arguments> operationArguments(){
        return Stream.of(Arguments.of(new TransferBetweenCards("1111111111111111","01/27", "444",
                "2222222222222222", new Amount(233,"rub"))));

    }
    @ParameterizedTest
    @MethodSource("saveThreeOperation")
    @DisplayName("Проверка сохранения 3-х операций")
    public void saveSomeOperation(TransferBetweenCards transferBetweenCards){
        repositoryTransfer.saveOperation(new TransferBetweenCards("3333333333333333", "01/28", "555",
                "5555555555555555", new Amount(1337, "rub")));
        repositoryTransfer.saveOperation(new TransferBetweenCards("3333333333333334", "03/29", "666",
                "5555555555555556", new Amount(1337, "rub")));
        repositoryTransfer.saveOperation(new TransferBetweenCards("3333333333333335", "04/30", "777",
                "5555555555555557", new Amount(1337, "rub")));
        String result = repositoryTransfer.saveOperation(transferBetweenCards);
        String expected = "3";
        Assertions.assertEquals(result,expected);

    }
    private static Stream<Arguments> saveThreeOperation(){
        return Stream.of(Arguments.of(new TransferBetweenCards("3333333333333333", "01/28", "555",
                "5555555555555555", new Amount(1337, "rub"))),
                Arguments.of(new TransferBetweenCards("3333333333333334", "03/29", "666",
                        "5555555555555556", new Amount(1337, "rub"))),
                Arguments.of(new TransferBetweenCards("3333333333333335", "04/30", "777",
                        "5555555555555557", new Amount(1337, "rub"))));

    }
}
