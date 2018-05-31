package com.example.parking.components;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.example.parking.components.data.ParkingData;
import com.example.parking.model.Parking;

import io.swagger.annotations.ApiOperation;

@Component
@Path("/parking")
public class ParkingController {
	
	private final ParkingService service;

	public ParkingController(ParkingService service) {
		this.service = service;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a parking instance")
	public Response createParking(ParkingData parkingData) {
		Parking parking = service.createParking(parkingData);
		return Response.ok(parking).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailableBays(@PathParam("id") Long id) {
		long free = service.getAvailableBays(id);
		return Response.ok(free).build();
	}
	
	@PUT
	@Path("/{id}/park/{carType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response parkCar(@PathParam("id") Long id, @PathParam("carType") char carType) {
		Integer index = service.parkCar(id, carType);
		return Response.ok(index).build();
	}
	
	@PUT
	@Path("/{id}/unpark/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unparkCar(@PathParam("id") Long id, @PathParam("index") Integer index) {
		boolean unparked = service.unparkCar(id, index);
		return Response.ok(unparked).build();
	}
	
	@GET
	@Path("/{id}/print")
	@Produces(MediaType.APPLICATION_JSON)
	public Response print(@PathParam("id") Long id) {
		String parkingMap = service.printParking(id);
		return Response.ok(parkingMap).build();
	}
}
