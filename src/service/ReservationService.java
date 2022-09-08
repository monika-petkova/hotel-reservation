package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationService {
    private final Map<String, IRoom> roomMap;
    private final Map<String, Set<Reservation>> reservations;
    private static final ReservationService instance = new ReservationService();

    private ReservationService() {
        this.roomMap = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    public static ReservationService getInstance() {
        return ReservationService.instance;
    }

    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return roomMap.get(roomId);
    }

    public List<IRoom> getAllRooms() {
        return new ArrayList<>(roomMap.values());
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        Reservation currentReservation = new Reservation(customer, room, checkinDate, checkoutDate);
        String roomId = room.getRoomNumber();

        Set<Reservation> reservationsSet = new HashSet<>();

        if (reservations.containsKey(roomId)) {
            reservationsSet = reservations.get(roomId);
        }

        reservationsSet.add(currentReservation);
        reservations.put(room.getRoomNumber(), reservationsSet);

        return currentReservation;
    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return getAllRooms()
                .stream()
                .filter(room -> {
                    Set<Reservation> reservationSet = reservations.get(room.getRoomNumber());
                    return reservationSet
                            .stream()
                            .map(reservation -> {
                                return checkInDate.after(reservation.getCheckinDate()) && checkInDate.before(reservation.getCheckoutDate()) ||
                                        checkOutDate.before(reservation.getCheckoutDate()) && checkOutDate.after(reservation.getCheckinDate());
                            })
                            .reduce(true, (result, current) -> result && current);
                }).collect(Collectors.toList());
    }

    public List<Reservation> getCustomersReservation(Customer customer) {
        return reservations.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(reservation -> reservation.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public void printAllReservation() {
        reservations.values().forEach(System.out::println);
    }
}
