# Parking
Parking management with Spring Boot

This is a Spring Boot application example based on a parking. This application provides some REST services for simple
operations like park and unpark a car. All operations are described in detail below. The persistence is implemented using an in-memory database with h2.

## Operations
#### Create parking
Creates a new parking with the given configuration.
#### Get number of available bays
Gets the number of available parking bays left
#### Park a car
Parks a car of the given type ('D' being dedicated to disabled people) in closest -to pedestrian exit- and first (starting from the parking's entrance)
available bay. Disabled people can only park on dedicated bays.
#### Unpark a car
Unparks the car from the given index
#### Print parking
Print a 2-dimensional representation of the parking with the following rules:
     <ul>
     <li>'=' is a pedestrian exit
     <li>'@' is a disabled-only empty bay
     <li>'U' is a non-disabled empty bay
     <li>'D' is a disabled-only occupied bay
     <li>the char representation of a parked vehicle for non-empty bays.
     </ul>

U, D, @ and = can be considered as reserved chars.

Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)

[Swagger definition](./parking-swagger.yaml)

## Not implemented
- Log
- Best error handling
- Validations
