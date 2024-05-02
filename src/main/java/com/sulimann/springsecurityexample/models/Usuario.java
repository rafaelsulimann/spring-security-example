package com.sulimann.springsecurityexample.models;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sulimann.springsecurityexample.utils.constants.TableName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = TableName.USUARIO)
@Getter
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;

  private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("UTC"));

  public Usuario(String username, String password) {
    this.username = username;
    this.password = new BCryptPasswordEncoder().encode(password);
  }

}
