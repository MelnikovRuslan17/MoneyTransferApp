package ru.netology.moneytranferapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytranferapp.model.ConfirmOperation;
import ru.netology.moneytranferapp.model.TransferBetweenCards;
import ru.netology.moneytranferapp.service.ServiceImpl;

@RestController
@RequestMapping
@CrossOrigin(origins = "${origins.host}")
@RequiredArgsConstructor

public class Controller {
    ServiceImpl service;

    public Controller(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferBetweenCards betweenCards) {
        return ResponseEntity.ok().body(service.transfer(betweenCards));
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<String> confirmOperation(@RequestBody ConfirmOperation confirmOperation) {
        return ResponseEntity.ok().body(service.confirmOperation(confirmOperation));
    }
}
//(https://serp-ya.github.io/card-transfer/)