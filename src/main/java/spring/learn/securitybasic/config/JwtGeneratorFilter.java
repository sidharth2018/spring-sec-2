package spring.learn.securitybasic.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtGeneratorFilter extends OncePerRequestFilter{
	
	private String key = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			SecretKey secretString  = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().claim("username",auth.getName())
					.signWith(secretString).compact();
			response.addHeader("JWT", jwt);
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
