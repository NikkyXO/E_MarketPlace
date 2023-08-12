package com.nikky.market.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nikky.market.entities.User;
import com.nikky.market.repositories.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Value("${app-jwt-secret}")
	private String secretKey;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private Long jwtExpiration;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private Long RefreshExpiration;
	
	
	
	
	public Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
				.parseClaimsJws(token).getBody();
	}
	
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	private Date extractExpirationDate(String token) {
		return extractClaim(token, Claims::getExpiration);
		
	}
	
	private boolean isTokenExpired(String token) {
		
		return extractExpirationDate(token).before(new Date());
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		
	}
	
	
	public String buildToken(UserDetails userDetails,
			Long expiration, Map<String,
			Object> extraClaims) {
		
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return buildToken(userDetails, jwtExpiration, extraClaims);
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		    return buildToken(userDetails, jwtExpiration, new HashMap<>());
		  }
	
	public String generateToken(UserDetails userDetails) {
	    return generateToken(new HashMap<>(), userDetails);
	  }
	
	
	
	

}
