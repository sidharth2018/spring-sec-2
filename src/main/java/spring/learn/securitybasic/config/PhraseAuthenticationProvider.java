package spring.learn.securitybasic.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class PhraseAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		Authentication authenticated = new PhraseAuthenticationToken("abc@123", null,null);
		return authenticated;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return PhraseAuthenticationToken.class.equals(authentication);
	}

}
