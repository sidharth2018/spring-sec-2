package spring.learn.securitybasic.config;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class PhraseAuthenticationToken extends AbstractAuthenticationToken {
	
	private final Object principal;

	private Object credentials;
	
	public PhraseAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
	
	public PhraseAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // must use super, as we override
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.principal;
	}
	
	
}
