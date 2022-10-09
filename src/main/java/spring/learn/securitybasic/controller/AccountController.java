package spring.learn.securitybasic.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.learn.securitybasic.repository.CustomerRepository;


@RestController
public class AccountController {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	CustomerRepository repository;
	
	@GetMapping("/myaccount")
	public String getMyAccount() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName().toString();
		System.out.println(username);
		List<String> beansN = Arrays.asList(context.getBeanDefinitionNames());
		Map<String, AuthenticationProvider> beansM = context.getBeansOfType(AuthenticationProvider.class);
		beansN.stream().filter(n->(n.contains("Authen") || n.contains("authen"))).forEach(System.out::println);
		
		return username;
	}

}
