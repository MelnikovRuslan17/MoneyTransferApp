package ru.netology.moneytranferapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.moneytranferapp.model.Amount;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.repository.RepositoryTransferImpl;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferAppApplicationTests {

@Autowired
TestRestTemplate restTemplate;
    @Autowired
    RepositoryTransferImpl repositoryTransfer;
    private GenericContainer<?> myApp = new GenericContainer<>("myapp:2.0")
            .withExposedPorts(5500);

    @BeforeEach
    public void setUp(){
        myApp.start();
    }
    @Test
    void contextLoadsTransferApp() {
        Integer myAppPort = myApp.getMappedPort(5500);

        final String urlTransfer = "http://127.0.0.1:" + myAppPort + "/transfer";
        final String urlConfirm = "http://127.0.0.1:" + myAppPort + "/confirmOperation";

        try {
            URI uriTransfer = new URI(urlTransfer);
            URI uriConfirm = new URI(urlConfirm);

            TransferBetweenCards transferBetweenCards = new TransferBetweenCards("1234123412341234",
                    "12/26", "123", "4321432143214321", new Amount(1000, "rub"));
            OperationConfirmation operationConfirmation = new OperationConfirmation("0", "0000");

            HttpEntity<TransferBetweenCards> requestTransfer = new HttpEntity<>(transferBetweenCards);
            HttpEntity<OperationConfirmation> requestConfirm = new HttpEntity<>(operationConfirmation);

            ResponseEntity<String> resultTransfer = restTemplate.postForEntity(uriTransfer, requestTransfer, String.class);
            ResponseEntity<String> resultConfirm = restTemplate.postForEntity(uriConfirm, requestConfirm, String.class);

            Assertions.assertEquals(HttpStatus.OK, resultTransfer.getStatusCode());
            Assertions.assertEquals(HttpStatus.OK, resultConfirm.getStatusCode());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
