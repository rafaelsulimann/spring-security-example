package com.sulimann.springsecurityexample.usecases.usuario.criar;

import java.util.Set;
import java.util.stream.Collectors;

import com.sulimann.springsecurityexample.models.Usuario;
import com.sulimann.springsecurityexample.repositories.RoleRepository;
import com.sulimann.springsecurityexample.utils.constants.ErrorMessage;
import com.sulimann.springsecurityexample.utils.validators.senhalimpa.SenhaLimpa;
import com.sulimann.springsecurityexample.utils.validators.uniquevalue.UniqueValue;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @Valid
  @NotNull(message = ErrorMessage.CAMPO_OBRIGATORIO)
  private Set<CriarUsuarioRoleRequest> roles;

  public Usuario toModel(RoleRepository roleRepository) {
    return new Usuario(this.username, this.password, this.roles.stream().map(roleRequest -> roleRequest.toModel(roleRepository)).collect(Collectors.toSet()));
  }

}
