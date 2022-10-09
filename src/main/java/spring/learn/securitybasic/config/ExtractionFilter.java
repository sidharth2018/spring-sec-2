package spring.learn.securitybasic.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExtractionFilter extends OncePerRequestFilter {
	
	private AuthenticationManager manager;
	
	public ExtractionFilter(AuthenticationManager manager) {
		this.manager=manager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String phrase  = request.getHeader("phrase");
		String pass = request.getHeader("pass");
		if(phrase!=null) {
			PhraseAuthenticationToken token = new PhraseAuthenticationToken(null, phrase);
			Authentication result = manager.authenticate(token);
			if(result.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(result);
			}
		}
		else if(pass!=null) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(pass, null);
		}
		
		filterChain.doFilter(request, response);

	}

}
