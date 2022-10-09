package spring.learn.securitybasic.config;

import java.security.Provider;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import spring.learn.securitybasic.service.CustomerSecurityService;

@Configuration
public class ProjectSecurityConfigurer{
	
	@Autowired
	List<AuthenticationProvider> providers;
	
	@Autowired
	AuthenticationConfiguration config;
	
	
	@Bean
	SecurityFilterChain projectSecSpecs(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers("/myaccount").authenticated()
		.antMatchers("/contact","/login").permitAll();
		http.httpBasic(Customizer.withDefaults());
		http.addFilterBefore(getExtFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}
	
	
//	@Bean
//	JdbcUserDetailsManager usersInMemory(DataSource datasource) {
//		return new JdbcUserDetailsManager(datasource);
//	}
	
//	@Bean
//	DaoAuthenticationProvider getDaoBean(CustomerSecurityService service,PasswordEncoder encoder) {
//		 DaoAuthenticationProvider daoProvider= new DaoAuthenticationProvider();
//		 daoProvider.setUserDetailsService(service);
//		 daoProvider.setPasswordEncoder(encoder);
//		 return daoProvider;
//		
//	}
	
	@Bean
	public ProviderManager getManagerBean() throws Exception {
		ProviderManager manager = new ProviderManager(providers);
		
		return manager;
	}
	
	@Bean
	public ExtractionFilter getExtFilter() throws Exception {
		return new ExtractionFilter(getManagerBean());
	}
	
	
//	@Bean
//	PhraseAuthenticationProvider pProvider() {
//		return new PhraseAuthenticationProvider();
//	}
//	
//	@Bean
//	CDaoAuthenticationProvider cProvider() {
//		return new CDaoAuthenticationProvider();
//	}

	
	@Bean
	PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	

}
