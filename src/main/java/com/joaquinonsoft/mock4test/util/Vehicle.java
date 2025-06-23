package com.joaquinonsoft.mock4test.util;

import com.joaquinonsoft.mock4test.Field;
import com.joaquinonsoft.mock4test.Mock4TestException;
import com.joaquinonsoft.mock4test.Person;
import com.joaquinonsoft.mock4test.dto.Car;
import com.joaquinonsoft.mock4test.dto.FamilyName;
import com.joaquinonsoft.mock4test.dto.Job;
import com.joaquinonsoft.mock4test.dto.Name;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Getter
public class Vehicle extends Field {
    @Getter(AccessLevel.NONE)
    private final static Logger log = LoggerFactory.getLogger(Vehicle.class);

    @Getter(AccessLevel.NONE)
    private volatile static List<Car> vehicles;

    private String brandName;
    private String familyName;
    private String modelName;
    private Date manufacturedFrom;
    private Date manufacturedTo;
    private String typeName;
    private String typeFullName;
    private String energy;

    public Vehicle() throws Mock4TestException {
        this(Locale.forLanguageTag("es-ES"));
    }

    public Vehicle(Locale locale) throws Mock4TestException {
        super(locale);
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws Mock4TestException {
        if(vehicles == null) {
            synchronized (this) {
                if(vehicles == null) {
                    try {
                        vehicles = (List<Car>) loadCSV("vehicles/male-name", Car.class);
                    } catch (FileNotFoundException e) {
                        throw new Mock4TestException(e);
                    }
                }
            }
        }
    }
}
