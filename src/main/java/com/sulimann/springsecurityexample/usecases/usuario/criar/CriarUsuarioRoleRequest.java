package com.sulimann.springsecurityexample.usecases.usuario.criar;

import com.sulimann.springsecurityexample.models.Role;
import com.sulimann.springsecurityexample.repositories.RoleRepository;
import com.sulimann.springsecurityexample.utils.constants.ErrorMessage;
import com.sulimann.springsecurityexample.utils.validators.existsbyid.ExistsById;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CriarUsuarioRoleRequest {

  @NotNull(message = ErrorMessage.CAMPO_OBRIGATORIO)
  @ExistsById(domainClass = Role.class, fieldName = "id", message = "Role não encontrada")
  private Long authorityId;

  public Role toModel(RoleRepository roleRepository){
    return roleRepository.findById(this.authorityId).get();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CriarUsuarioRoleRequest other = (CriarUsuarioRoleRequest) obj;
    if (authorityId == null) {
      if (other.authorityId != null)
        return false;
    } else if (!authorityId.equals(other.authorityId))
      return false;
    return true;
  }

}
