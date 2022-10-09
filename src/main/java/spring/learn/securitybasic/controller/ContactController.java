package spring.learn.securitybasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.learn.securitybasic.repository.CustomerRepository;

@RestController
public class ContactController {
	@Autowired
	CustomerRepository repository;
	@GetMapping("/contact")
	public String addEnquiry() {
		return repository.findByEmail("abc@123").get(0).getEmail();
	}

}
