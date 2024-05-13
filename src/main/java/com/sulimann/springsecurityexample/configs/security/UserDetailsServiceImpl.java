package com.sulimann.springsecurityexample.configs.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sulimann.springsecurityexample.models.Usuario;
import com.sulimann.springsecurityexample.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

  private final UsuarioRepository repository;

  public UserDetailsServiceImpl(UsuarioRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.repository.findByUsernameFetchRoles(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
  }

  public Usuario usuarioAutenticado(){
    String username = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    return this.repository.findByUsernameFetchRoles(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
  }

  public void isUsuarioAutorizado(Long usuarioId){
    Usuario usuarioAutenticado = this.usuarioAutenticado();
    if(!usuarioAutenticado.hasRole("ADMIN") && !usuarioAutenticado.getId().equals(usuarioId)){
      throw new ForbiddenException("Acesso negado");
    }
  }

}
