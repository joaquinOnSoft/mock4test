package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.dto.VehicleDTO;
import com.joaquinonsoft.mock4test.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class VehicleTest {

    @Test
    public void constructor(){
        try {
            Vehicle vehicle = new Vehicle();
            assertNotNull(vehicle.getBrandName());
        } catch (Mock4TestException e) {
            fail(e);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadCSV(){
        List<VehicleDTO> cars;
        try {
            Vehicle vehicle = new Vehicle(Locale.FRENCH,
                    DateUtil.nowYearMonthXYearsAgo(5),
                    DateUtil.nowYearMonth(),
                    Brand.values());

            for(Brand brand: Brand.values()){
                cars = (List<VehicleDTO>) vehicle.loadCSV( "vehicles/" + brand.toString(), VehicleDTO.class, true);
                assertNotNull(cars);
            }
        } catch (Mock4TestException | FileNotFoundException e) {
            fail(e);
        }
    }
}
