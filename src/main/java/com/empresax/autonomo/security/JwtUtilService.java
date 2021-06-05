package com.empresax.autonomo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.empresax.autonomo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
	@Value("${autonomo.jwt.secret}")
	private String secret;
	
	public String generate(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();

		long oneDay = 86400000L;
		Date iat = new Date();
		Date exp = new Date( iat.getTime() + oneDay );
		
		@SuppressWarnings("deprecation")
		String jwt = Jwts.builder()
								.setIssuer("AutonomoWebService")
								.setSubject(user.getId().toString())
								.setIssuedAt(iat)
								.setExpiration(exp)
								.signWith(SignatureAlgorithm.HS256, secret)
								.compact();
		
		return String.format("Bearer %s", jwt);
	}
	
}
