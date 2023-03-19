package ru.netology.moneytranferapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter


public class Amount {
    private int value;
    private String currency;
}
