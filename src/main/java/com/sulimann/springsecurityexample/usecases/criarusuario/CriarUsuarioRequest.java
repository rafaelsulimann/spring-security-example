package com.sulimann.springsecurityexample.usecases.criarusuario;

import com.sulimann.springsecurityexample.models.Usuario;
import com.sulimann.springsecurityexample.utils.constants.ErrorMessage;
import com.sulimann.springsecurityexample.utils.validators.senhalimpa.SenhaLimpa;
import com.sulimann.springsecurityexample.utils.validators.uniquevalue.UniqueValue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CriarUsuarioRequest {

  @NotBlank(message = ErrorMessage.CAMPO_OBRIGATORIO)
  @UniqueValue(domainClass = Usuario.class, fieldName = "username", message = "Usuario já existente")
  private String username;

  @NotBlank(message = ErrorMessage.CAMPO_OBRIGATORIO)
  @Size(min = 6, message = "Password deve contêr no mínimo 6 caracteres")
  @SenhaLimpa(message = "Senha não pode estar encodada")
  private String password;

  public Usuario toModel() {
    return new Usuario(this.username, this.password);
  }

}
