package com.sulimann.springsecurityexample.usecases.init;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.springsecurityexample.configs.security.UserDetailsServiceImpl;
import com.sulimann.springsecurityexample.utils.constants.Path;

@RestController
@RequestMapping(value = Path.INIT)
public class InitController {

    private final UserDetailsServiceImpl authService;

    public InitController(UserDetailsServiceImpl authService) {
        this.authService = authService;
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> init(@PathVariable Long id){
        authService.isUsuarioAutorizado(id);
        return ResponseEntity.status(HttpStatus.OK).body("Get realizado com sucesso!");
    }

}
