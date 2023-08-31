package com.cpyproject2spring.demo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {
    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }

}
