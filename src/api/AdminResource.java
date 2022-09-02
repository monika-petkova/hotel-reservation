package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.List;

public class AdminResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private static AdminResource resource;

    private AdminResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static void initInstance(CustomerService customerService, ReservationService reservationService) {
        AdminResource.resource = new AdminResource(customerService, reservationService);
    }

    public static AdminResource getInstance() throws Exception {
        if (AdminResource.resource == null) {
            throw new Exception("Resource not initialized");
        }
        return AdminResource.resource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public List<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
