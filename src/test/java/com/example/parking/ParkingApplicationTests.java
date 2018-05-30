package com.example.parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingApplicationTests extends SpringBootServletInitializer{

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
        //SpringApplication.run(ParkingApplicationTests.class, args);
		new ParkingApplicationTests()
		.configure(new SpringApplicationBuilder(ParkingApplicationTests.class))
		.run(args);
		
        ParkingBuilderTest pbt = new ParkingBuilderTest();
        pbt.testBuildBasicParking();
    }
}
