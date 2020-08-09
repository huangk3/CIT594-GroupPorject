package edu.upenn.cit594.data;

import java.time.ZonedDateTime;

public class Parking extends SingleData{
	private String timestamp;
	private Double fine;
	private String description;
	private String vehicleID;
	private String state;
	private String violationID;
	

	public Parking(String zipcodeIn, String timestamp, String fine, String description, 
			String vehicleID, String state, String violationID) {
		super(zipcodeIn);
		this.timestamp = ZonedDateTime.parse(timestamp).toInstant().toString();
		
		try {
			this.fine = Double.parseDouble(fine);
		}catch(NumberFormatException e) {
			this.fine = null;
			
		}
		
		
		this.description = description;
		this.vehicleID = vehicleID;
		this.state = state;
		this.violationID = violationID;
		
	}


	public String getTimestamp() {
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


}
