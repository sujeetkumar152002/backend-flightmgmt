package com.BookingMicroservice.Model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Flight {

    private Long id;
    private String flightId;
    private LocalDate flightDate;
    private String origin;
    private String destination;
    private int totalNoOfSeats;
    private int availableSeats;
    private String status;
    private LocalTime flightTime;
    private List<Seat> seats;

    public Flight() {
    }

    public Flight(Long id, String flightId, LocalDate flightDate, String origin, String destination,
                  int totalNoOfSeats, int availableSeats, String status, LocalTime flightTime, List<Seat> seats) {
        this.id = id;
        this.flightId = flightId;
        this.flightDate = flightDate;
        this.origin = origin;
        this.destination = destination;
        this.totalNoOfSeats = totalNoOfSeats;
        this.availableSeats = availableSeats;
        this.status = status;
        this.flightTime = flightTime;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalNoOfSeats() {
        return totalNoOfSeats;
    }

    public void setTotalNoOfSeats(int totalNoOfSeats) {
        this.totalNoOfSeats = totalNoOfSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(LocalTime flightTime) {
        this.flightTime = flightTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightId='" + flightId + '\'' +
                ", flightDate=" + flightDate +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", totalNoOfSeats=" + totalNoOfSeats +
                ", availableSeats=" + availableSeats +
                ", status='" + status + '\'' +
                ", flightTime=" + flightTime +
                ", seats=" + seats +
                '}';
    }
}
