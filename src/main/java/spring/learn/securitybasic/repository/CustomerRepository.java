package spring.learn.securitybasic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.learn.securitybasic.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public List<Customer> findByEmail(String email);

}
