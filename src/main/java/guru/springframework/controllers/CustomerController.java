package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cooijen on 17-7-2017.
 */

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * This API call will show all customers in JSON format
     */
    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("customers", customerService.listAllCustomers());
        return "customers";
    }

    /**
     * This API call will show new created customer in JSON format
     */
    @RequestMapping(value = "/api/customer/add", method = RequestMethod.POST)
    public String add(Model model, @RequestBody Customer customer) {
        model.addAttribute("customer", customer);
        return "redirect:/customers/";
    }

    /**
     * This API call will show selected customer for editing in JSON format
     */
    @RequestMapping(value = "/api/customer/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", customerService.getCustomerById(id));
        return "customerform";
    }

    /**
     * This API call will show saved customer in JSON format
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveCustomer(Customer customer){
        customerService.saveCustomer(customer);

        return "redirect:/api/customer/" + customer.getId();
    }

    /**
     * This API call will show deleted customer in JSON format
     */
    @RequestMapping(value = "/api/customer/delete/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable Integer id) {
        Customer c = customerService.getCustomerById(id);
        customerService.deleteCustomer(c);
        return "redirect:/api/customers";
    }

    /**
     * This API call will show selected customer in JSON format
     */
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public String showCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customershow";
    }
}
