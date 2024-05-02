package com.sulimann.springsecurityexample.usecases.usuario.criar;

import com.sulimann.springsecurityexample.models.Role;

import lombok.Getter;

@Getter
public class CriarUsuarioRoleResponse {

  private Long id;
  private String authority;

  public CriarUsuarioRoleResponse(Role role){
    this.id = role.getId();
    this.authority = role.getAuthority();
  }

}
