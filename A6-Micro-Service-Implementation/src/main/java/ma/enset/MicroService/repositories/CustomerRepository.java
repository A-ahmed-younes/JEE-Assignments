package ma.enset.MicroService.repositories;

import ma.enset.MicroService.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
