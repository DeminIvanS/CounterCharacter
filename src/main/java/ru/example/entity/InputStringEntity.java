package ru.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputStringEntity {

    private final InputStringEntity inputString;
    private String string;


}
