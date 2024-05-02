package com.sulimann.springsecurityexample.configs.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

}
