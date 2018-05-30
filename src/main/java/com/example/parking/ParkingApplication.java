package com.example.parking;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ParkingApplication extends SpringBootServletInitializer {

	public static void main(String[] args)  {
		new ParkingApplication()
		.configure(new SpringApplicationBuilder(ParkingApplication.class))
		.run(args);
	}
}
