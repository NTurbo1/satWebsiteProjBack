package com.nturbo1.satWebsiteProjBack.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//@Value("${application.security.jwt.secret-key}")
	  private String secretKey = "89bfc5951aeceb31d9b01f33bd35cf116b1d53c0650de18c4eefbaaa7d87b23f";
	  //@Value("${application.security.jwt.expiration}")
	  private long jwtExpiration = 1000 * 60 * 24;
	 // @Value("${application.security.jwt.refresh-token.expiration}")
	  private long refreshExpiration;

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }
	  
//	  public String extractRoles(String token) { //TODO: Implement this.
//		return extractClaim(token, Claims::get)
//	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	  }

	  public String generateToken(UserDetails userDetails) {
	    return generateToken(new HashMap<>(), userDetails);
	  }

	  public String generateToken(
	      Map<String, Object> extraClaims,
	      UserDetails userDetails
	  ) {
	    return buildToken(extraClaims, userDetails, jwtExpiration);
	  }

	  public String generateRefreshToken(
	      UserDetails userDetails
	  ) {
	    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
	  }

	  private String buildToken(
	          Map<String, Object> extraClaims,
	          UserDetails userDetails,
	          long expiration
	  ) {
	    return Jwts
	            .builder()
	            .setClaims(extraClaims)
	            .setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration))
	            .claim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
	            )
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	  }

	  public boolean isTokenValid(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	  }

	  private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }

	  private Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }

	  private Claims extractAllClaims(String token) {
	    return Jwts
	        .parserBuilder()
	        .setSigningKey(getSignInKey())
	        .build()
	        .parseClaimsJws(token)
	        .getBody();
	  }

	  private Key getSignInKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	    return Keys.hmacShaKeyFor(keyBytes);
	  }
}
