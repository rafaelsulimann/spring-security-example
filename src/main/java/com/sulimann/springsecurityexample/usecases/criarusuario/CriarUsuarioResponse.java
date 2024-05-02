package com.sulimann.springsecurityexample.usecases.criarusuario;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sulimann.springsecurityexample.models.Usuario;

import lombok.Getter;

@Getter
public class CriarUsuarioResponse {

  private Long id;
  private String username;
  private String password;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private LocalDateTime creationDate;

  public CriarUsuarioResponse(Usuario usuario) {
    this.id = usuario.getId();
    this.username = usuario.getUsername();
    this.password = usuario.getPassword();
    this.creationDate = usuario.getCreationDate();
  }

}
