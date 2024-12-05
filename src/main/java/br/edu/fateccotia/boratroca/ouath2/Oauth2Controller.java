package br.edu.fateccotia.boratroca.ouath2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

    public ResponseEntity<String> retornaTolken(String token) {
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
