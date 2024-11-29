package br.edu.fateccotia.boratroca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokenteste")
public class TokenTesteController {
    @PostMapping("/teste")
    public ResponseEntity<String> mostrarToken(@RequestBody String token) {
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
