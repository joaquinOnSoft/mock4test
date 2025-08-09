package com.openmock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class VehicleLoaderTest {

    @Test
    public void getVehicle(){
        try {
            VehicleLoader loader = new VehicleLoader();
            assertNotNull(loader.getVehicle());
            assertNotNull(loader.getVehicle().getBrandName());
        } catch (OpenMockException e) {
            fail(e);
        }
    }
}
