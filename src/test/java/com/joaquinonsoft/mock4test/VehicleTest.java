package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.dto.Car;
import com.joaquinonsoft.mock4test.util.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.fail;

public class VehicleTest {
    @Test
    public void loadCSV(){
        try {
            Vehicle vehicle = new Vehicle(Locale.FRENCH);
            for(Brand brand: Brand.values()){
                vehicle.loadCSV( "vehicles/" + brand.toString(), Car.class);
            }
        } catch (Mock4TestException | FileNotFoundException e) {
            fail(e);
        }

    }
}
