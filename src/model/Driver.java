package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com");
        System.out.println(customer);

        try {
            Customer customerInvalidEmail = new Customer("first", "second", "email");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
