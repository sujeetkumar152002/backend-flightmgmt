package com.checkin.model;

public class PassengerDetails {

	private Integer passengerId;
	private String passengerName;
	private int seatNumber;
	private String gender;
	private int age;
	private String flightId;
	private String seatClass;

	public PassengerDetails() {
		super();
	}

	public PassengerDetails(Integer passengerId, String passengerName, int seatNumber, String gender, int age,
							String flightId, String seatClass) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.seatNumber = seatNumber;
		this.gender = gender;
		this.age = age;
		this.flightId = flightId;
		this.seatClass = seatClass;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
}
