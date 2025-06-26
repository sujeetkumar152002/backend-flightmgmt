package com.SearchMicroservice.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.SearchMicroservice.Entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
 List<Flight> findByOriginIgnoreCase(String origin);
 List<Flight> findByDestinationIgnoreCase(String destination);
 List<Flight> findByFlightDate(LocalDate flightDate);
 List<Flight> findByFlightTime(LocalTime flightTime);

 Flight findByFlightIdAndFlightDate(String flightId, LocalDate flightDate);
 @Query("SELECT f FROM Flight f JOIN f.seats s WHERE " +
	       "LOWER(f.origin) = LOWER(:origin) AND " +
	       "LOWER(f.destination) = LOWER(:destination) AND " +
	       "f.flightDate = :flightDate AND " +
	       "LOWER(s.seatClass) = LOWER(:seatClass)")
	List<Flight> findByOriginAndDestinationAndFlightDateAndSeatClass(
	    @Param("origin") String origin,
	    @Param("destination") String destination,
	    @Param("flightDate") LocalDate flightDate,
	    @Param("seatClass") String seatClass);


}

