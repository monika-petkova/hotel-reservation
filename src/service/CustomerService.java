package service;

import api.HotelResource;
import model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {
    private Map<String, Customer> customerMap;
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
        this.customerMap = new HashMap<>();
    }

    public static CustomerService getInstance() throws Exception {
        if (CustomerService.instance == null) {
            throw new Exception("Service not initialized");
        }
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
