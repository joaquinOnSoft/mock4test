package com.openmock;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class AirlineLoaderTest {

    @Test
    public void getAirline() {
        try {
            //Get airline in Spanish with the predefined airlines (mainly European airlines)
            AirlineLoader loader = new AirlineLoader();
            getAirlineFromAirlineLoader(loader);
        } catch (OpenMockException e) {
            fail(e);
        }
    }
    
    public void getAirlineUsingAllAirlines() {
        try {
            //Get airline in English from the full list of available airlines
            AirlineLoader loader = new AirlineLoader(Locale.UK, AirlineName.values());
            getAirlineFromAirlineLoader(loader);
        } catch (OpenMockException e) {
            fail(e);
        }
    }

    private static void getAirlineFromAirlineLoader(AirlineLoader loader) {
        Airline airline = loader.getAirline();

        assertNotNull(airline);
        assertNotNull(airline.getCode());
        assertNotNull(airline.getName());
    }

}
