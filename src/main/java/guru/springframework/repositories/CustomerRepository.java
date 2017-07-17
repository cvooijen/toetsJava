package guru.springframework.repositories;

import guru.springframework.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cooijen on 17-7-2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
