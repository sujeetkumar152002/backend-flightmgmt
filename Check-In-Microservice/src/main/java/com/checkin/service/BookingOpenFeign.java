package com.checkin.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.checkin.model.Booking;
import com.checkin.model.PassengerDetails;

@FeignClient(name = "Booking-Service" )
public interface BookingOpenFeign {
	
	@GetMapping("/book/bookings/{flightId}")
	public List<Booking> getBookings(@PathVariable String flightId);

	@GetMapping("/book/getByBookingId/{bookingId}")
	public List<Booking> getBookingsById(@PathVariable Integer bookingId);
	
	@GetMapping("/book/getPassengerById/{passengerId}")
	PassengerDetails getPassengerDetailsById(@PathVariable Integer passengerId);
	
}
