package com.openmock;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class PersonLoaderTest {
    @Test
    public void getValues(){
        try {
            PersonLoader loaderES = new PersonLoader();
            Person personES = loaderES.getPerson();
            assertNotNull(personES.getFirstName());
            assertNotNull(personES.getLastName());
            assertNotNull(personES.getSecondLastName());
            assertNotNull(personES.getFullName());
            assertNotNull(personES.getNationalIdentityCard());
            assertNotNull(personES.getSex());
            assertNotNull(personES.getBirthdate());

            PersonLoader loaderUK = new PersonLoader(Locale.UK);
            Person personUK = loaderUK.getPerson();
            assertNotNull(personUK.getFirstName());
            assertNotNull(personUK.getLastName());
            assertNull(personUK.getSecondLastName());
            assertNotNull(personUK.getFullName());
            assertNotNull(personUK.getNationalIdentityCard());
            assertNotNull(personUK.getSex());
            assertNotNull(personUK.getBirthdate());
        } catch (OpenMockException e) {
            fail(e);
        }

    }
}
