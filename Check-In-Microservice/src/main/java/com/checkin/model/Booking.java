package com.checkin.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Booking {

	private Integer bookingId;

	private String flightId;

	private Integer passengerBookingId;

	private Integer noOfSeats;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookingDate;

	private String bookingStatus;

	private List<PassengerDetails> passengers;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Integer getPassengerBookingId() {
		return passengerBookingId;
	}

	public void setPassengerBookingId(Integer passengerBookingId) {
		this.passengerBookingId = passengerBookingId;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public List<PassengerDetails> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}

	public Booking(Integer bookingId, String flightId, Integer passengerBookingId, Integer noOfSeats,
			LocalDateTime bookingDate, String bookingStatus, List<PassengerDetails> passengers) {
		super();
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.passengerBookingId = passengerBookingId;
		this.noOfSeats = noOfSeats;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.passengers = passengers;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", flightId=" + flightId + ", passengerBookingId="
				+ passengerBookingId + ", noOfSeats=" + noOfSeats + ", bookingDate=" + bookingDate + ", bookingStatus="
				+ bookingStatus + ", passengers=" + passengers + "]";
	}

	

}
