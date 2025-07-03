package com.isft194.gestin.jwt;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.isft194.gestin.models.User;

@Component
public class JwtUtil {

  @Value("${secret.jwt}")
  private String SECRET_KEY;

  private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

  public String generateToken(User user) {
    return Jwts
      .builder()
      .claims(new HashMap<>())
      .claim("id", user.getId())
      .claim("name", user.getNames())
      .claim("type", user.getIsTeacher())
      .subject(user.getEmail())
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(getKey())
      .compact();
  }

  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
    return Jwts
      .parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getBody()
      .getSubject();
  }

    public boolean validateToken(String token) {
      try {
        Jwts
          .parser()
          .verifyWith(getKey())
          .build()
          .parseSignedClaims(token);
        return true;
      } catch (JwtException | IllegalArgumentException e) {
        return false;
      }
    }
}
