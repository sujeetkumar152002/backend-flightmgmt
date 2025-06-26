package com.BookingMicroservice.Service;



import java.util.HashMap;
import java.util.List;

import com.BookingMicroservice.Model.Booking;
import com.BookingMicroservice.Model.PassengerDetails;

public interface BookingService {

    List<Booking> getBookingByPassengerBookingId(Integer passengerBookingId);

    List<Booking> getBookingsById(Integer bookingId);

    HashMap<String, String> showAvailableSeats(String flightId);

    int showAvailableSeatsBySeatClass(String flightId, String seatclass);

    int addBooking(String flightId, String seatclass, int noOfSeats, Booking booking);

    int deleteBookingByFlightId(String flightId);

    int deleteBookingByBookingId(Integer bookingId);

    List<Booking> getBookingByFlightId(String flightId);

    int deletePassengerIdByBookingId(Integer bookingId, Integer passengerId);

    double calculateTotalPrice(Integer noOfSeats, String seatclass, String flightId);

    PassengerDetails getPassengerDetailsById(Integer passengerId);
}
