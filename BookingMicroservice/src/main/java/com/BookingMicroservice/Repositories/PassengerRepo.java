package com.BookingMicroservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookingMicroservice.Model.PassengerDetails;

@Repository
public interface PassengerRepo extends JpaRepository<PassengerDetails, Integer> {
	
	void deleteByPassengerId(Integer passengerId);

	PassengerDetails findByPassengerId(Integer passengerId);
}
