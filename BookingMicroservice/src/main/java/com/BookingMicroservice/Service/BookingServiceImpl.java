package com.BookingMicroservice.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookingMicroservice.Model.Booking;
import com.BookingMicroservice.Model.Flight;
import com.BookingMicroservice.Model.PassengerDetails;
import com.BookingMicroservice.Repositories.BookingRepo;
import com.BookingMicroservice.Repositories.PassengerRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private FlightClient flightClient;

    @Override
    public List<Booking> getBookingByPassengerBookingId(Integer passengerBookingId) {
        LOGGER.info("Fetching Bookings By Passenger Booking ID {}", passengerBookingId);
        List<Booking> bookings = bookingRepo.findByPassengerBookingId(passengerBookingId);
        LocalDateTime nowDateTime = LocalDateTime.now();
        for (Booking booking : bookings) {
            for (PassengerDetails passenger : booking.getPassengers()) {
                passenger.setBooking(booking);
            }
        }
        return bookings.stream().filter(booking -> booking.getBookingDate().isAfter(nowDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<String, String> showAvailableSeats(String flightId) {
        LOGGER.info("Fetching Available Seats with flight ID {}", flightId);
        HashMap<String, Integer> mapp = flightClient.getAvailableSeats(flightId);
        HashMap<String, String> updatedMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mapp.entrySet()) {
            updatedMap.put(entry.getKey(), entry.getValue() == 0 ? "Seats are Not Available" : entry.getValue() + " seats are Available");
        }
        return updatedMap;
    }

//    @Override
//    public int showAvailableSeatsBySeatClass(String flightId, String seatclass) {
//        LOGGER.info("Fetching Available Seats with flight ID {} and Seat class {}", flightId, seatclass);
//        return flightClient.getAvailableSeatsByClass(flightId, seatclass);
//    }

//    @Override
//    public int addBooking(String flightId, String seatclass, int noOfSeats, Booking booking) {
//        LOGGER.info("Fetching Flight if available with ID {}", flightId);
//        Flight flight = flightClient.showFlightDetailsById(flightId);
//        if (flight == null) {
//            LOGGER.error("Flight not found for ID: {}", flightId);
//            return -1;
//        }
//
//        Integer availableSeats = flightClient.getAvailableSeatsByClass(flightId, seatclass);
//        if (availableSeats < noOfSeats) {
//            LOGGER.warn("Not enough seats available. Requested: {}, Available: {}", noOfSeats, availableSeats);
//            return -1;
//        }
//
//        if (!"Scheduled".equals(flightClient.getFlightStatus(flightId))) {
//            LOGGER.warn("Flight {} is not Scheduled", flightId);
//            return -3;
//        }
//
//        List<PassengerDetails> list = booking.getPassengers();
//        if (noOfSeats != list.size()) {
//            LOGGER.warn("Passenger list size mismatch with requested seats");
//            return -2;
//        }
//
//        booking.setBookingStatus("Confirmed");
//        booking.setNoOfSeats(noOfSeats);
//        int seatno = availableSeats;
//        for (PassengerDetails pass : list) {
//            pass.setSeatNumber(seatno--);
//            pass.setSeatClass(seatclass);
//            pass.setBooking(booking);
//        }
//
//        flightClient.updateAvailableSeats(flightId, noOfSeats, seatclass);
//        return bookingRepo.save(booking) != null ? 1 : -1;
//    }
    @Override
    public int addBooking(String flightId, String seatclass, int noOfSeats, Booking booking) {
        LOGGER.info("Fetching Flight if available with ID {}", flightId);
        Flight flight = flightClient.showFlightDetailsById(flightId);
        if (flight == null) {
            LOGGER.error("Flight not found for ID: {}", flightId);
            return -1;
        }

        Integer availableSeats = flightClient.getAvailableSeatsByClass(flightId, seatclass);
        if (availableSeats < noOfSeats) {
            LOGGER.warn("Not enough seats available. Requested: {}, Available: {}", noOfSeats, availableSeats);
            return -1;
        }

        if (!"Scheduled".equals(flightClient.getFlightStatus(flightId))) {
            LOGGER.warn("Flight {} is not Scheduled", flightId);
            return -3;
        }

        List<PassengerDetails> list = booking.getPassengers();
        if (noOfSeats != list.size()) {
            LOGGER.warn("Passenger list size mismatch with requested seats");
            return -2;
        }

        booking.setBookingStatus("Confirmed");
        booking.setNoOfSeats(noOfSeats);

        // Example: 6 seats per row (A-F)
        int seatsPerRow = 6;
        char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F'};
        int totalAssigned = 0;
        int currentRow = (availableSeats - 1) / seatsPerRow + 1;
        int seatIndex = (availableSeats - 1) % seatsPerRow;

        for (PassengerDetails pass : list) {
            String seatNumber = currentRow + "" + seatLetters[seatIndex];
            pass.setSeatNumber(seatNumber);
            pass.setSeatClass(seatclass);
            pass.setBooking(booking);

            totalAssigned++;
            seatIndex--;
            if (seatIndex < 0) {
                seatIndex = seatsPerRow - 1;
                currentRow--;
            }
        }

        flightClient.updateAvailableSeats(flightId, noOfSeats, seatclass);
        return bookingRepo.save(booking) != null ? 1 : -1;
    }


    @Override
    public int deleteBookingByFlightId(String flightId) {
        List<Booking> bookings = bookingRepo.findByFlightId(flightId);
        if (bookings.isEmpty()) return -1;
        bookingRepo.deleteByFlightId(flightId);
        return 1;
    }

    @Override
    public List<Booking> getBookingByFlightId(String flightId) {
        return bookingRepo.findByFlightId(flightId);
    }

    @Override
    public int deleteBookingByBookingId(Integer bookingId) {
        List<Booking> bookingList = bookingRepo.findByBookingId(bookingId);
        if (bookingList.isEmpty()) return 0;

        for (Booking booking : bookingList) {
            booking.setBookingStatus("Cancelled");
            bookingRepo.save(booking);
            for (PassengerDetails pass : booking.getPassengers()) {
                flightClient.updateAvailableSeatsAfterDeletion(booking.getFlightId(), 1, pass.getSeatClass());
            }
        }
        return 1;
    }

    @Override
    public List<Booking> getBookingsById(Integer bookingId) {
        return bookingRepo.findByBookingId(bookingId);
    }

    @Override
    public int deletePassengerIdByBookingId(Integer bookingId, Integer passengerId) {
        List<Booking> bookings = bookingRepo.findByBookingId(bookingId);
        for (Booking booking : bookings) {
            Iterator<PassengerDetails> iterator = booking.getPassengers().iterator();
            while (iterator.hasNext()) {
                PassengerDetails pass = iterator.next();
                if (pass.getPassengerId().equals(passengerId)) {
                    iterator.remove();
                    passengerRepo.deleteByPassengerId(passengerId);
                    flightClient.updateAvailableSeatsAfterDeletion(booking.getFlightId(), 1, pass.getSeatClass());
                    booking.setPassengers(booking.getPassengers());
                    bookingRepo.save(booking);
                    return 1;
                }
            }
        }
        return -1;
    }

    @Override
    public double calculateTotalPrice(Integer noOfSeats, String seatclass, String flightId) {
        double seatPrice = flightClient.getPrice(flightId, seatclass);
        return noOfSeats * seatPrice;
    }

    @Override
    public PassengerDetails getPassengerDetailsById(Integer passengerId) {
        return passengerRepo.findByPassengerId(passengerId);
    }

    @Override
    public int showAvailableSeatsBySeatClass(String flightId, String seatclass) {
        LOGGER.info("Fetching Available Seats with flight ID {} and Seat class {}", flightId, seatclass);
        try {
            Integer available = flightClient.getAvailableSeatsByClass(flightId, seatclass);
            return available != null ? available : 0;
        } catch (Exception e) {
            LOGGER.error("Error fetching available seats for flightId: {} and seatClass: {}. {}", flightId, seatclass, e.getMessage());
            return 0;
        }
    }

}
