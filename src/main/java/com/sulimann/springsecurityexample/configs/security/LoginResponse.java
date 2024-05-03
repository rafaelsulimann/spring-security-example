package com.sulimann.springsecurityexample.configs.security;

public record LoginResponse(
    String accessToken,
    String tokenType,
    Long expiresIn,
    String scope,
    String jti
) {

}
