package edu.upenn.cit594.data;
import java.time.LocalDateTime;

public class Parking {
	private LocalDateTime timestamp;
	private Double fine;
	private String description;
	private String vehicleID;
	private String state;
	private String violationID;
	private String zipcode;
	

	public Parking(String timestamp, String fine, String description, 
			String vehicleID, String state, String violationID, String zipcode) {
		this.timestamp = LocalDateTime.parse(timestamp);
		this.fine = Double.parseDouble(fine);
		this.description = description;
		this.vehicleID = vehicleID;
		this.state = state;
		this.violationID = violationID;
		this.zipcode = zipcode;
		
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Double getFine() {
		return fine;
	}


	public String getDescription() {
		return description;
	}


	public String getVehicleID() {
		return vehicleID;
	}


	public String getState() {
		return state;
	}


	public String getViolationID() {
		return violationID;
	}


	public String getZipcode() {
		return zipcode;
	}

}
