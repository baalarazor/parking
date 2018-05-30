package com.example.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args)  {
		/*new ParkingApplication()
		.configure(new SpringApplicationBuilder(ParkingApplication.class))
		.run(args);*/
		SpringApplication.run(ParkingApplication.class, args);
		
	}
}
