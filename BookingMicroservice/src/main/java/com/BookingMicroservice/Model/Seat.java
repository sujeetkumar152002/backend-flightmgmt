package com.BookingMicroservice.Model;

public class Seat {

    private Long seatId;
    private String seatNumber;
    private String seatClass;
    private String seatStatus;
    private double seatPrice;  // ✅ Added seatPrice field
    private Flight flight;     // Refers to the Flight POJO you've created

    public Seat() {
    }

    public Seat(Long seatId, String seatNumber, String seatClass, String seatStatus, double seatPrice, Flight flight) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.seatStatus = seatStatus;
        this.seatPrice = seatPrice;
        this.flight = flight;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatClass='" + seatClass + '\'' +
                ", seatStatus='" + seatStatus + '\'' +
                ", seatPrice=" + seatPrice +
                ", flight=" + flight +
                '}';
    }
}
