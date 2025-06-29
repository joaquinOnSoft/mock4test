package com.openmock;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void getValues(){
        try {
            Person personES = new Person();
            assertNotNull(personES.getFirstName());
            assertNotNull(personES.getLastName());
            assertNotNull(personES.getSecondLastName());
            assertNotNull(personES.getFullName());
            assertNotNull(personES.getNationalIdentityCard());
            assertNotNull(personES.getSex());
            assertNotNull(personES.getBirthdate());

            Person personUK = new Person(Locale.UK);
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
