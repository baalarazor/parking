package com.example.parking.components;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.example.parking.components.data.ParkingData;
import com.example.parking.model.Parking;

@Component
@Path("/parking")
public class ParkingController {
	
	private final ParkingService service;

	public ParkingController(ParkingService service) {
		this.service = service;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createParking(ParkingData parkingData) {
		Parking parking = service.createParking(parkingData);
		return Response.ok(parking).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailableBays(@PathParam("id") Long id) {
		Optional<Parking> parkingOpt = service.getParkingById(id);
		if(parkingOpt.isPresent()) {
			Parking parking = parkingOpt.get();
			long free = service.getAvailableBays(parking.getId());
			return Response.ok(free).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	
}
