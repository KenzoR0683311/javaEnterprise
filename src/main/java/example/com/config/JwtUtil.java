package example.com.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
  private static final int expireTimeInMinutes = 24 * 60 * 60 * 1000;
  @Value("${jwt.token.secret}")
  private String secret;

	public String generate(String username) {
		return Jwts.builder()
      .setSubject(username)
      .setIssuer("Kenzo")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() * expireTimeInMinutes))
      .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor(secret.getBytes()))
      .compact();
	}

  public boolean validate(String token) {
    return getUsername(token) != null && isExpired(token); 
  }

  public String getUsername(String token) {
    Claims claims = getClaims(token);
    return claims.getSubject();
  }

  public boolean isExpired(String token) {
    Claims claims = getClaims(token);
    return claims.getExpiration().after(new Date(System.currentTimeMillis()));
  }

  private Claims getClaims(String token) {
	  return Jwts.parserBuilder()
      .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}

