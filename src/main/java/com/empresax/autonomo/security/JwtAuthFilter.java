package com.empresax.autonomo.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.empresax.autonomo.model.User;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtUtilService jwtService;
	
	public JwtAuthFilter(JwtUtilService jwtService) {
		this.jwtService = jwtService;
	}
	
	private class TokenValidation {
		
		private final boolean isValid;
		private final String jwt;
		
		public TokenValidation(boolean isValid, String jwt) {
			this.isValid = isValid;
			this.jwt = jwt;
		}
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String bearerToken = request.getHeader("Authorization");
		
		TokenValidation tokenValidation = isTokenValid(bearerToken);
		
		if(tokenValidation.isValid) {
			
			Optional<User> user = jwtService.getUserBytoken(tokenValidation.jwt);
			
			if(user.isPresent()) {
				
				User principal = user.get();
				
				Authentication credentials = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(credentials);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private TokenValidation isTokenValid(String bearerToken) {
		
		if(bearerToken == null || bearerToken.trim().isEmpty() || !bearerToken.startsWith("Bearer ")) {
			
			return new TokenValidation( false , null );

		}
		
		String jwt = bearerToken.substring(7, bearerToken.length());
		
		return new TokenValidation( jwtService.isTokenValid(jwt) , jwt );
		
	}

}

