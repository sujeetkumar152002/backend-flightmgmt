package com.BookingMicroservice.Controller;


import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookingMicroservice.Exception.BookingHandlingException;
import com.BookingMicroservice.Exception.BookingsNotFoundException;
import com.BookingMicroservice.Exception.FlightNotFoundException;
import com.BookingMicroservice.Model.Booking;
import com.BookingMicroservice.Model.PassengerDetails;
import com.BookingMicroservice.Service.BookingService;
import com.BookingMicroservice.Service.RazorClient;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RazorClient razorClient;

	@GetMapping("/bookingsOfPassenger/{passengerBookingId}")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable Integer passengerBookingId) throws BookingsNotFoundException{
		List<Booking> bookings = bookingService.getBookingByPassengerBookingId(passengerBookingId);
		if(bookings.size()== 0) {
			throw new BookingsNotFoundException("No Bookings Available");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	@GetMapping("/getPassengerById/{passengerId}")
	public ResponseEntity<PassengerDetails> getPassengerById(@PathVariable Integer passengerId) throws BookingsNotFoundException{
		PassengerDetails pass = bookingService.getPassengerDetailsById(passengerId);
		if(pass == null) {
			throw new BookingsNotFoundException("Passenger Not Found");
		}
		return ResponseEntity.ok(pass);
	}

	@GetMapping("/getByBookingId/{bookingId}")
	public ResponseEntity<List<Booking>> getBookingsById(@PathVariable Integer bookingId) throws BookingsNotFoundException{
		List<Booking> bookings = bookingService.getBookingsById(bookingId);
		if(bookings.size() == 0) {
			throw new BookingsNotFoundException("No Bookings Available for bookingId "+bookingId);
		}
		return ResponseEntity.ok(bookings);
	}

	@GetMapping("/bookings/{flightId}")
	public ResponseEntity<List<Booking>> getBookings(@PathVariable String flightId) throws BookingsNotFoundException{
		List<Booking> bookings = bookingService.getBookingByFlightId(flightId);
		if(bookings.size()== 0) {
			throw new BookingsNotFoundException("No Bookings Available");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	@GetMapping("/showSeats/{flightId}")
	public HashMap<String, String> showAvailableSeats(@PathVariable String flightId){
		return bookingService.showAvailableSeats(flightId);
	}

	@GetMapping("/showSeats/{flightId}/{seatclass}")
	public ResponseEntity<String> showAvailableSeatsBySeatClass(@PathVariable String flightId,@PathVariable String seatclass) throws BookingsNotFoundException {
		int noOfSeats = bookingService.showAvailableSeatsBySeatClass(flightId, seatclass);
		if(noOfSeats == -1) {
			throw new BookingsNotFoundException("No Flight Exists with No "+flightId);
		}
		if(noOfSeats == -2) {
			throw new BookingsNotFoundException("Seats Not available");
		}
		return ResponseEntity.status(HttpStatus.OK).body("No Of Seats Available for "+seatclass+" is "+noOfSeats );
	}

	@PostMapping("/bookTickets/{flightId}/{seatclass}/{noOfSeats}")
	@Transactional
	public ResponseEntity<String> addBooking(@PathVariable String flightId,
			@PathVariable String seatclass,
			@PathVariable int noOfSeats
			,@RequestBody @Valid Booking booking) throws BookingsNotFoundException, BookingHandlingException, FlightNotFoundException{

		int no = bookingService.addBooking(flightId,seatclass,noOfSeats,booking);
		if(no == 1) {
			double price = bookingService.calculateTotalPrice(noOfSeats, seatclass, flightId);
			return ResponseEntity.ok("Order created successfully. Please pay amount "+price + " Booking Successfully");
		}else if(no == -2) {
			throw new BookingHandlingException("Booking Unsuccesfully \nPlease add Passengers based on Number of seats mentioned");
		}else if(no == -3) {
			throw new BookingHandlingException("Flight is Cancelled\nBooking Not available..");
		}
		throw new BookingHandlingException("Booking Unsuccesfully");
	}

	@DeleteMapping("/deleteBooking/{flightId}")
	public ResponseEntity<String> deleteBooking(@PathVariable String flightId) throws BookingHandlingException{

		int x = bookingService.deleteBookingByFlightId(flightId);
		if(x == 1) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
		}
		throw new BookingHandlingException("Flight is not available ");
	}

	@DeleteMapping("/deleteBookingById/{bookingId}")
	public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingId) throws BookingHandlingException{

		int x = bookingService.deleteBookingByBookingId(bookingId);
		if(x == 1) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
		}
		throw new BookingHandlingException("Booking Id is not available ");
	}

	@DeleteMapping("/deletePassenger/{bookingId}/{passengerId}")
	public ResponseEntity<String> deletePassengerByBookingId(@PathVariable Integer bookingId, @PathVariable Integer passengerId) throws BookingHandlingException{
		int x = bookingService.deletePassengerIdByBookingId(bookingId, passengerId);
		if(x == 1) {
			return ResponseEntity.ok("Deleted Passenger Successfully");
		}
		throw new BookingHandlingException("Deletion Unsuccessful");
	}
}
