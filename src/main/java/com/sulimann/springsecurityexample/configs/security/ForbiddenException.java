package com.sulimann.springsecurityexample.configs.security;

public class ForbiddenException extends RuntimeException{

  private static final Long serialVersionUID = 1L;

  public ForbiddenException(String msg){
    super(msg);
  }

}
