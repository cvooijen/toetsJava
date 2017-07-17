package guru.springframework.services;

import guru.springframework.domain.Customer;

/**
 * Created by cooijen on 17-7-2017.
 */
public interface CustomerService {
    Iterable<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer product);

    void deleteCustomer(Customer product);
}