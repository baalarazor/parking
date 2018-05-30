package com.example.parking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.parking.components.ParkingService;
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingBuilder;

public class ParkingBuilderTest {
	
	private final ParkingService service = new ParkingService();

    @Test
    public void testBuildBasicParking() {
        final Parking parking = new ParkingBuilder().withSquareSize(4).build();
        assertEquals(16, service.getAvailableBays(parking));
    }

    @Test
    public void testBuildParkingWithPedestrianExit() {
        final Parking parking = new ParkingBuilder().withSquareSize(3).withPedestrianExit(5).build();
        assertEquals(8, service.getAvailableBays(parking));
    }

    @Test
    public void testBuildParkingWithDisabledSlot() {
        final Parking parking = new ParkingBuilder().withSquareSize(2).withDisabledBay(2).build();
        assertEquals(4, service.getAvailableBays(parking));
    }

    @Test
    public void testBuildParkingWithPedestrianExitsAndDisabledSlots() {
        final Parking parking = new ParkingBuilder().withSquareSize(10).withPedestrianExit(8).withPedestrianExit(42).withPedestrianExit(85).withDisabledBay(2)
            .withDisabledBay(47).withDisabledBay(72).build();
        assertEquals(97, service.getAvailableBays(parking));
    }
}
