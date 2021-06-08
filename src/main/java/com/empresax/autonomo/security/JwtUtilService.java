package com.empresax.autonomo.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.model.User;
import com.empresax.autonomo.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${autonomo.jwt.secret}")
	private String secret;
	
	@SuppressWarnings("deprecation")
	public String generate(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();

		long oneDay = 86400000L;
		Date iat = new Date();
		Date exp = new Date( iat.getTime() + oneDay );
		
		String jwt = Jwts.builder()
								.setIssuer("AutonomoWebService")
								.setSubject(user.getId().toString())
								.setIssuedAt(iat)
								.setExpiration(exp)
								.signWith(SignatureAlgorithm.HS256, secret)
								.compact();
		
		return String.format("Bearer %s", jwt);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isTokenValid(String token) {
		try {
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
			
		} catch (Exception e) {
			
			return false;
			
		}
	}
	
	public Optional<User> getUserBytoken(String token) {
		
		try {
			
			Jws<Claims> jws = Jwts.parserBuilder()
				.setSigningKey(secret)
				.build()
				.parseClaimsJws(token);
			
		    // we can safely trust the JWT
			Long id = Long.parseLong( jws.getBody().getSubject() );	
			
			Optional<User> user = this.userRepository.findById(id);
			if(user.isPresent()) {
				
				return user;
			
			}
			
			return Optional.empty();
			
		} catch(JwtException e) {
			
			return Optional.empty();
			
		}
		
	}
	
}
