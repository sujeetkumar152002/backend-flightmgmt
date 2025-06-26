package com.BookingMicroservice.Repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookingMicroservice.Model.Booking;



@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    List<Booking> findByPassengerBookingId(Integer passengerBookingId);

    List<Booking> findByFlightId(String flightId);  // updated from flightNumber

    void deleteByFlightId(String flightId);  // updated from flightNumber

    void deleteByBookingId(Integer bookingId);

    List<Booking> findByBookingId(Integer bookingId);
}
