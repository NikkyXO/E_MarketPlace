package com.nikky.market.services;

import com.nikky.market.repositories.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Value("${app-jwt-secret}")
	private String secretKey;
	
//	@Value("${app-jwt-expiration-milliseconds}")
//	private Long jwtExpiration;
//
//	@Value("${app-jwt-expiration-milliseconds}")
//	private Long RefreshExpiration;
	

	
	// Create Signing Key
	public Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
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

	// checks username exists and not expired
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		
	}
	
	
	public String buildToken(UserDetails userDetails,
							 Map<String, Object> extraClaims) {
		
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + + 1000 * 60 * 24))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken(Map<String, Object> extraClaims,
								UserDetails userDetails) {
		return buildToken(userDetails, extraClaims);
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		    return buildToken(userDetails, new HashMap<>());
		  }
	
	public String generateToken(UserDetails userDetails) {
	    return generateToken(new HashMap<>(), userDetails);
	  }
	
	
	
	

}
