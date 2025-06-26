package com.checkin.service;

import java.util.List;

import com.checkin.model.CheckIn;

public interface CheckInService {

	public int addCheckIn(CheckIn checkIn);
	
	public boolean isCheckedIn(Integer passengerId);

		public List<CheckIn> getAllCheckIns(String flightId);
		
		public int deleteCheckInByFlightId(String flightId);
		
		int checkIn(Integer passengerId);
}
