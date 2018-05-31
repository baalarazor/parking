package com.example.parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.parking.components.ParkingService;
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingBuilder;
import com.example.parking.model.ParkingRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingTest {
    private static final int FIRSTUPEDESTRIANUEXITUINDEX = 8;

    private Long idParking;
    
    @Autowired
    private ParkingService parkingService;
    
    @Autowired
    private ParkingRepository parkingRepository;

    @Before
    public void setUp() {
        Parking parking = new ParkingBuilder().withSquareSize(5).withPedestrianExit(FIRSTUPEDESTRIANUEXITUINDEX).withPedestrianExit(12).withDisabledBay(5)
            .withDisabledBay(10).build();
        idParking = parking.getId();
        parkingRepository.save(parking);
    }

    @Test
    public void testGetAvailableBays() {
        assertEquals(23, parkingService.getAvailableBays(idParking));
    }

    @Test
    public void testParkCarVehiculeTypeC() {
        assertEquals(Integer.valueOf(7), parkingService.parkCar(idParking, 'C'));
        assertEquals(22, parkingService.getAvailableBays(idParking));
    }

    @Test
    public void testParkCarVehiculeTypeM() {
        assertEquals(Integer.valueOf(7), parkingService.parkCar(idParking, 'M'));
        assertEquals(22, parkingService.getAvailableBays(idParking));
    }

    @Test
    public void testParkCarTwoVehicules() {
    	assertEquals(Integer.valueOf(7), parkingService.parkCar(idParking, 'C'));
        assertEquals(22, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(9), parkingService.parkCar(idParking, 'M'));
        assertEquals(21, parkingService.getAvailableBays(idParking));
    }

    @Test
    public void testParkCarDisabled() {
        assertEquals(Integer.valueOf(10), parkingService.parkCar(idParking, 'D'));
        assertEquals(22, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(5), parkingService.parkCar(idParking, 'D'));
        assertEquals(21, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(-1), parkingService.parkCar(idParking, 'D'));
        assertEquals(21, parkingService.getAvailableBays(idParking));
    }

    @Test
    public void testUnparkCar() {
        final int firstCarBayIndex = parkingService.parkCar(idParking, 'C');
        assertTrue(parkingService.unparkCar(idParking, firstCarBayIndex));
        assertEquals(23, parkingService.getAvailableBays(idParking));
        assertFalse(parkingService.unparkCar(idParking, firstCarBayIndex));

        final int secondCarBayIndex = parkingService.parkCar(idParking, 'D');
        assertTrue(parkingService.unparkCar(idParking, secondCarBayIndex));
        assertEquals(23, parkingService.getAvailableBays(idParking));
        assertFalse(parkingService.unparkCar(idParking, secondCarBayIndex));

        assertFalse(parkingService.unparkCar(idParking, FIRSTUPEDESTRIANUEXITUINDEX));
    }

    @Test
    public void testToString() {
        assertEquals("UUUUU\nU=UU@\n@U=UU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
    }

    @Test
    public void testCompleteSolution() {
        assertEquals(Integer.valueOf(7), parkingService.parkCar(idParking, 'C'));
        assertEquals("UUUUU\nU=CU@\n@U=UU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(22, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(9), parkingService.parkCar(idParking, 'C'));
        assertEquals("UUUUU\nC=CU@\n@U=UU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(21, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(11), parkingService.parkCar(idParking, 'M'));
        assertEquals("UUUUU\nC=CU@\n@M=UU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(20, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(13), parkingService.parkCar(idParking, 'M'));
        assertEquals("UUUUU\nC=CU@\n@M=MU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(19, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(10), parkingService.parkCar(idParking, 'D'));
        assertEquals("UUUUU\nC=CU@\nDM=MU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(18, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(5), parkingService.parkCar(idParking, 'D'));
        assertEquals("UUUUU\nC=CUD\nDM=MU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(17, parkingService.getAvailableBays(idParking));

        assertEquals(Integer.valueOf(-1), parkingService.parkCar(idParking, 'D'));
        assertEquals("UUUUU\nC=CUD\nDM=MU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(17, parkingService.getAvailableBays(idParking));

        assertFalse(parkingService.unparkCar(idParking, 3));
        assertEquals("UUUUU\nC=CUD\nDM=MU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(17, parkingService.getAvailableBays(idParking));

        assertTrue(parkingService.unparkCar(idParking, 13));
        assertEquals("UUUUU\nC=CUD\nDM=UU\nUUUUU\nUUUUU", parkingService.printParking(idParking));
        assertEquals(18, parkingService.getAvailableBays(idParking));
    }
}
