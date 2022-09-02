package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Date;
import java.util.List;

public class HotelResource {
    private final CustomerService customerService;
    private final ReservationService reservationService;
    private static HotelResource resource;

    private HotelResource(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static void initInstance(CustomerService customerService, ReservationService reservationService) {
        HotelResource.resource = new HotelResource(customerService, reservationService);
    }

    public static HotelResource getInstance() throws Exception {
        if (HotelResource.resource == null) {
            throw new Exception("Resource not initialized");
        }
        return HotelResource.resource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public List<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public List<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
