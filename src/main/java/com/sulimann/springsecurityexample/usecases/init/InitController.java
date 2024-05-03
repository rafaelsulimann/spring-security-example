package com.sulimann.springsecurityexample.usecases.init;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.springsecurityexample.utils.constants.Path;

@RestController
public class InitController {

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping(value = Path.INIT)
    public ResponseEntity<Object> init(){
        return ResponseEntity.status(HttpStatus.OK).body("Get realizado com sucesso!");
    }

}
