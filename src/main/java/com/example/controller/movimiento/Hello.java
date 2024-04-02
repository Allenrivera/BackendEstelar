package com.example.controller.movimiento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    String message;

    @GetMapping("/hola")
    public String holamundo (){
        System.out.println(message);
        return "Hola, mundo";
    }
}
