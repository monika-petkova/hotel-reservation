package service;

import model.Customer;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<String, Customer> customerMap;
    private static final CustomerService service = new CustomerService();

    private CustomerService() {
        this.customerMap = new HashMap<>();
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

    public Map<String, Customer> getAllCustomers() {
        return customerMap;
    }

    public static CustomerService getCustomerService() {
        return service;
    }
}
