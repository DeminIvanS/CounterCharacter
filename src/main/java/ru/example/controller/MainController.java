package ru.example.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.model.InputStringEntity;
import ru.example.service.InputString;

@RestController
@RequestMapping("/api/v1/counter")
@AllArgsConstructor
public class MainController {

    private final InputString service;

    @PostMapping("get_count_char_from_string")
    public String getCountCharFromString(@RequestBody InputStringEntity string){
        return service.getCountChars(string);
    }


}