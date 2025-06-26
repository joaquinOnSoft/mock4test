package com.joaquinonsoft.mock4test.dto;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.*;


/// DTO describing a chemical element (from the periodic table)
/// @see [OpenCSV-Custom Field Binding Annotation](http://learn-java-spring-hibernate.blogspot.com/2017/08/opencsv-custom-field-binding-annotation.html)
/// @see [Read CSV File to Java Bean (using Open CSV)](https://sunitc.dev/2020/05/31/read-csv-file-to-java-bean-using-open-csv/)
@Getter
@Setter
@NoArgsConstructor
public class ElementDTO {
    @CsvBindByName
    private int atomicNumber;
    //Element's name
    @CsvBindByName(column = "Element")
    private String name;
    @CsvBindByName
    private String symbol;
    @CsvBindByName
    private float atomicMass;
    @CsvBindByName
    private int numberOfNeutrons;
    @CsvBindByName
    private int numberOfProtons;
    @CsvBindByName
    private int numberOfElectrons;
    @CsvBindByName
    private int period;
    @CsvBindByName
    private int group;
    @CsvBindByName
    private String phase;
    @CsvBindByName
    //Radioactive: yes/(empty)
    @CsvCustomBindByName(column="Radioactive", converter=YesNo2BooleanConverter.class)
    private boolean radioactive;
    //Natural: yes/(empty)
    @CsvCustomBindByName(column="Natural", converter=YesNo2BooleanConverter.class)
    private boolean natural;
    //Metal: yes/(empty)
    @CsvCustomBindByName(column="Metal", converter=YesNo2BooleanConverter.class)
    private boolean metal;
    @CsvBindByName
    //Non metal: yes/(empty)
    @CsvCustomBindByName(column="Nonmetal", converter=YesNo2BooleanConverter.class)
    private boolean nonmetal;
    @CsvBindByName
    //Metalloid: yes/(empty)
    @CsvCustomBindByName(column="Metalloid", converter=YesNo2BooleanConverter.class)
    private boolean metalloid;
    @CsvBindByName
    private String Type;
    @CsvBindByName
    private float atomicRadius;
    @CsvBindByName
    private float electronegativity;
    @CsvBindByName
    private float  firstIonization;
    @CsvBindByName
    private double density;
    @CsvBindByName
    private float meltingPoint;
    @CsvBindByName
    private float boilingPoint;
    @CsvBindByName
    private int numberOfIsotopes;
    @CsvBindByName
    private String discoverer;
    @CsvBindByName
    private int year;
    @CsvBindByName
    private float specificHeat;
    @CsvBindByName
    private int numberOfShells;
    @CsvBindByName
    private int numberOfValence;


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static class YesNo2BooleanConverter extends AbstractBeanField {

        @Override
        protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
            return value != null && value.compareToIgnoreCase("yes") == 0;
        }
    }
}
