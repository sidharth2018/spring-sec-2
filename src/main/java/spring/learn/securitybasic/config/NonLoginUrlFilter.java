package spring.learn.securitybasic.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class NonLoginUrlFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getServletPath().equalsIgnoreCase("/login") || request.getHeader("JWT")!=null) {
			filterChain.doFilter(request, response);
		}
		else {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getOutputStream().write("Not Authenticated".getBytes());
		return;
	}
		
	}

}
