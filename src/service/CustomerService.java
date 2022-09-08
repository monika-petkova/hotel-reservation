package service;

import model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private Map<String, Customer> customerMap;
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
        this.customerMap = new HashMap<>();
    }

    public static CustomerService getInstance() {
        return CustomerService.instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        if (!customerMap.containsKey(email)) {
            customerMap.put(email, customer);
        }
    }

    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }
}
