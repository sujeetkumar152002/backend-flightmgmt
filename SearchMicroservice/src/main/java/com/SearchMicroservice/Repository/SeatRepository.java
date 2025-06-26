package com.SearchMicroservice.Repository;

import com.SearchMicroservice.Entities.Flight;
import com.SearchMicroservice.Entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightIdAndSeatStatusIgnoreCase(Long Id, String seatStatus);
    List<Seat> findBySeatStatus(String status);
    List<Seat> findBySeatClass(String seatClass);
    Seat getSeatByFlightAndSeatNumber(Flight flight, String seatNumber);

    
    List<Seat> findByFlight_FlightIdAndSeatClassIgnoreCaseAndSeatStatusIgnoreCase(
            String flightId, String seatClass, String seatStatus);
    Seat findByFlightAndSeatNumberIgnoreCase(Flight flight, String seatNumber);

    Optional<Seat> findBySeatId(Long seatId);

    Optional<Seat> findBySeatNumber(String seatNumber);
    
    @Query("SELECT s.seatPrice FROM Seat s WHERE LOWER(s.seatClass) = LOWER(:seatClass)")
    List<Double> findSeatPricesByClass(@Param("seatClass") String seatClass);

    Optional<Seat> findFirstBySeatClassIgnoreCase(String seatClass);
}



