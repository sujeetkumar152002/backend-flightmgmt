package com.BookingMicroservice.Service;


import java.util.HashMap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BookingMicroservice.Exception.CustomErrorDecoder;
import com.BookingMicroservice.Model.Flight;



@FeignClient(name = "SEARCH-SERVICE", configuration = CustomErrorDecoder.class)
public interface FlightClient {

    // ✅ Get available seats (all classes) for a flight
    @GetMapping("/flights/{flightId}/available-seats")
    HashMap<String, Integer> getAvailableSeats(@PathVariable String flightId);

    // ✅ Get available seats by class for a flight
    @GetMapping("/flights/seats/class/{seatClass}")
    Integer getAvailableSeatsByClass(@PathVariable String seatClass); // seatClass only, since no flightId is passed here in controller

    // ✅ Update available seats after booking
    @PutMapping("/flights/updateSeats")
    String updateAvailableSeats(
            @RequestParam String flightId,
            @RequestParam Integer seatsBooked,
            @RequestParam String seatClass);

    // ✅ Update available seats after cancellation
    @PutMapping("/flights/updateSeatsAfterDeletion")
    String updateAvailableSeatsAfterDeletion(
            @RequestParam String flightId,
            @RequestParam Integer seatsDeleted,
            @RequestParam String seatClass);

    // ✅ Get flight status by flightId
    @GetMapping("/flights/flightstatus/{flightId}")
    String getFlightStatus(@PathVariable String flightId);

    
    @GetMapping("/flights/{flightId}")
    Flight showFlightDetailsById(@PathVariable String flightId);

    // ✅ Get fare by flightId and seat class
    @GetMapping("/flights/getFareOfSeatClass")
    double getPrice(@RequestParam String flightId, @RequestParam String seatClass);
    
    
    @GetMapping("/flights/{flightId}/available-seats/class/{seatClass}")
    Integer getAvailableSeatsByClass(@PathVariable("flightId") String flightId,
                                     @PathVariable("seatClass") String seatClass);
	
}
