package com.sulimann.springsecurityexample.configs.security;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final JwtEncoder encoder;

  public JwtService(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  public LoginResponse generateToken(Authentication authentication){
    Instant now = Instant.now();
    Long expiry = 86400L;

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
        .id(UUID.randomUUID().toString())
        .build();

    String accessToken = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    return new LoginResponse(accessToken, "bearer", expiry, claims.getClaim("scope"), claims.getId());
  }

}
