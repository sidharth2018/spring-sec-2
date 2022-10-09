package spring.learn.securitybasic.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtValidatorFilter extends OncePerRequestFilter {
	
	private String key = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader("JWT");
		if(jwt!=null) {
			
			try {
				
				SecretKey sKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
				JwtParser p = Jwts.parserBuilder()
						 .setSigningKey(sKey)
						 .build();
				 Claims claim = p.parseClaimsJws(jwt)
						 .getBody();
				 String username = (String) claim.get("username");
				 Authentication auth = new UsernamePasswordAuthenticationToken(username, null,new ArrayList<>());
				 SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid Token details");
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

}
