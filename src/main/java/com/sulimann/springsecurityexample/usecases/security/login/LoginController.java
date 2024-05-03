package com.sulimann.springsecurityexample.usecases.security.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.springsecurityexample.configs.security.JwtService;
import com.sulimann.springsecurityexample.configs.security.LoginResponse;
import com.sulimann.springsecurityexample.utils.constants.Path;

@RestController
@RequestMapping(value = Path.LOGIN)
public class LoginController {

  private final JwtService jwtService;

  public LoginController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping
  public ResponseEntity<LoginResponse> login(Authentication autentication){
    return ResponseEntity.status(HttpStatus.OK).body(this.jwtService.generateToken(autentication));
  }

}
