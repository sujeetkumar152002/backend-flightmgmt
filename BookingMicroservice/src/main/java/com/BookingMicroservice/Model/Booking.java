package com.BookingMicroservice.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @NotBlank(message = "Flight ID is required")
    private String flightId;

    @NotNull(message = "Passenger Id is required")
    private Integer passengerBookingId;

    private Integer noOfSeats;

    @NotNull(message = "Booking Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingDate;

    private String bookingStatus;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PassengerDetails> passengers;

    public Booking() {
        super();
    }

    public Booking(@NotBlank(message = "Flight ID is required") String flightId,
                   @NotNull(message = "Passenger Id is required") Integer passengerBookingId,
                   @NotNull(message = "No Of Seats is required") Integer noOfSeats,
                   @NotNull(message = "Booking Date is required") LocalDateTime bookingDate,
                   String bookingStatus,
                   List<PassengerDetails> passengers) {
        super();
        this.flightId = flightId;
        this.passengerBookingId = passengerBookingId;
        this.noOfSeats = noOfSeats;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.passengers = passengers;
    }

    public Booking(String flightId, Integer passengerBookingId, LocalDateTime bookingDate,
                   String bookingStatus, List<PassengerDetails> passengers) {
        super();
        this.flightId = flightId;
        this.passengerBookingId = passengerBookingId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.passengers = passengers;
    }

    public Booking(String flightId, Integer passengerBookingId, LocalDateTime bookingDate, String bookingStatus) {
        super();
        this.flightId = flightId;
        this.passengerBookingId = passengerBookingId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

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

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", flightId=" + flightId + ", passengerBookingId="
                + passengerBookingId + ", noOfSeats=" + noOfSeats + ", bookingDate=" + bookingDate
                + ", bookingStatus=" + bookingStatus + ", passengers=" + passengers + "]";
    }
}
