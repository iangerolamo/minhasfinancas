package com.ig.minhasfinancas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/")
    public String helloWorld() {
        return "hello world!";
    }
}
