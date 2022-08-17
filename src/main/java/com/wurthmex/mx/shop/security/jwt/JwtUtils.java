package com.wurthmex.mx.shop.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wurthmex.mx.shop.entities.ShopUserApp;
import com.wurthmex.mx.shop.security.config.SecurityConstants;
import com.wurthmex.mx.shop.services.ShopUserAppServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;

@Component
public class JwtUtils {

  private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Autowired
  private ShopUserAppServiceImpl userAppServiceImpl;

  @Autowired
  private SecurityConstants securityConstants;

  public String generateJwtToken(Authentication authentication) {
    String token = "";

    try {
      ShopUserApp userPrincipal = new ShopUserApp();
      
      userPrincipal = userAppServiceImpl.findUserByUsername(authentication.getName());

      BeanUtils.copyProperties(authentication, userPrincipal);

      Map<String, Object> claims = new HashMap<>();
      claims.put("email", userPrincipal.getEmail());
      
      token = Jwts.builder()
          .setClaims(claims)
          .setSubject(userPrincipal.getUsername())
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + securityConstants.EXPIRATION_DATE))
          .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(securityConstants.getTokenSecret()))
          .compact();

    } catch (Exception e) {
      logger.error("error to create token ".concat(e.getMessage()));
    }

    return securityConstants.TOKEN_PREFIX.concat(token);
  }

  public String getUsernameFromJwtToken(String token) {
    // the prefix is removed from the token
    token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

    var username = Jwts
        .parser()
        .setSigningKey(TextCodec.BASE64.decode(securityConstants.getTokenSecret()))
        .parseClaimsJws(token)
        .getBody().getSubject();

    return username;
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(securityConstants.getTokenSecret()).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
