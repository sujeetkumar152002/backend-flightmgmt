package com.SearchMicroservice.Service;

import com.SearchMicroservice.Entities.Flight;
import com.SearchMicroservice.Exception.FlightNotFoundException;
import com.SearchMicroservice.Repository.FlightRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepository flightRepository;

    // Add a new flight
    public Flight addFlight(Flight flight) {
        logger.info("Adding new flight: {}", flight);
        return flightRepository.save(flight);
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        logger.info("Fetching all flights");
        return flightRepository.findAll();
    }

    // Get flight by ID
    public Flight getFlightById(Long id) {
        logger.info("Fetching flight with ID: {}", id);
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }

    // Get flights by origin
    public List<Flight> getFlightsByOrigin(String origin) {
        logger.info("Fetching flights from origin: {}", origin);
        return flightRepository.findByOriginIgnoreCase(origin.trim());
    }

    // Get flights by destination
    public List<Flight> getFlightsByDestination(String destination) {
        logger.info("Fetching flights to destination: {}", destination);
        return flightRepository.findByDestinationIgnoreCase(destination.trim());
    }

    // Get flights by departure date
    public List<Flight> getFlightsByFlightDate(LocalDate flightDate) {
        logger.info("Fetching flights with departure date: {}", flightDate);
        return flightRepository.findByFlightDate(flightDate);
    }

    // Get flights by time
    public List<Flight> getFlightsByFlightTime(LocalTime flightTime) {
        logger.info("Fetching flights with departure time: {}", flightTime);
        return flightRepository.findByFlightTime(flightTime);
    }

    // Get flight by flight number and date
    public Flight getFlightByFlightNumberAndDate(String flightId, LocalDate flightDate) {
        logger.info("Fetching flight by flight number {} and date {}", flightId, flightDate);
        Flight flight = flightRepository.findByFlightIdAndFlightDate(flightId, flightDate);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found with number: " + flightId + " on date: " + flightDate);
        }
        return flight;
    }

    // Search flights by multiple criteria
    public List<Flight> searchFlights(String origin, String destination, LocalDate flightDate, String seatClass) {
        logger.info("Searching flights from {} to {} on {} with seat class {}", origin, destination, flightDate, seatClass);
        return flightRepository.findByOriginAndDestinationAndFlightDateAndSeatClass(origin, destination, flightDate, seatClass);
    }

    // Update flight
    public Flight updateFlight(Long id, Flight updatedFlight) {
        logger.info("Updating flight with ID: {}", id);
        return flightRepository.findById(id).map(flight -> {
            flight.setFlightId(updatedFlight.getFlightId());
            flight.setFlightDate(updatedFlight.getFlightDate());
            flight.setOrigin(updatedFlight.getOrigin());
            flight.setDestination(updatedFlight.getDestination());
            flight.setTotalNoOfSeats(updatedFlight.getTotalNoOfSeats());
            flight.setAvailableSeats(updatedFlight.getAvailableSeats());
            flight.setStatus(updatedFlight.getStatus());
            flight.setFlightTime(updatedFlight.getFlightTime());
            flight.setSeats(updatedFlight.getSeats());
            return flightRepository.save(flight);
        }).orElseThrow(() -> new FlightNotFoundException("Flight not found with id " + id));
    }

    // Delete flight
    public void deleteFlight(Long id) {
        logger.info("Deleting flight with ID: {}", id);
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight not found with id " + id);
        }
        flightRepository.deleteById(id);
    }

	

}
