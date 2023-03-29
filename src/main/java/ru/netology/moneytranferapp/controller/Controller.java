package ru.netology.moneytranferapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytranferapp.model.OperationConfirmation;
import ru.netology.moneytranferapp.model.SuccessOK;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.service.ServiceTransferImpl;

@RestController
@RequestMapping
@CrossOrigin(origins = "${origins.host}")
@RequiredArgsConstructor

public class Controller {

    private final ServiceTransferImpl service;

    @PostMapping("transfer")
    public ResponseEntity<SuccessOK> transfer(@RequestBody TransferBetweenCards betweenCards) {
        return ResponseEntity.ok().body(new SuccessOK(service.transfer(betweenCards)));
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<SuccessOK> confirmOperation(@RequestBody OperationConfirmation operationConfirmation) {
        return ResponseEntity.ok().body(new SuccessOK(service.confirmOperation(operationConfirmation)));
    }

}
