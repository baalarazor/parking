package com.example.parking.components;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/parking")
public class ParkingController {
	
	private final ParkingService service;

	public ParkingController(ParkingService service) {
		this.service = service;
	}

	
	
}
