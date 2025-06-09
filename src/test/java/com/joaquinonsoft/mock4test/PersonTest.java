package com.joaquinonsoft.mock4test;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class PersonTest {
    @Test
    public void getValues(){
        try {
            Person person1 = new Person();
            assertNotNull(person1.getFirstName());
            assertNotNull(person1.getLastName());
            assertNotNull(person1.getSex());

            Person person2 = new Person(Locale.UK);
            assertNotNull(person2.getFirstName());
            assertNotNull(person2.getLastName());
            assertNotNull(person2.getSex());
        } catch (Mock4DataException e) {
            fail(e);
        }

    }
}
