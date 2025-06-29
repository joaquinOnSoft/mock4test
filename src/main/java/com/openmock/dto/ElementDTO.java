package com.openmock.dto;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


/// DTO describing a chemical element, from the periodic table.
/// [OpenCSV-Custom Field Binding Annotation](http://learn-java-spring-hibernate.blogspot.com/2017/08/opencsv-custom-field-binding-annotation.html)
/// [Read CSV File to Java Bean (using Open CSV)](https://sunitc.dev/2020/05/31/read-csv-file-to-java-bean-using-open-csv/)
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
    //Non-metal: yes/(empty)
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
    public static class YesNo2BooleanConverter extends AbstractBeanField<Boolean, String> {

        @Override
        protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
            //de-DE	Alemán (Alemania)	Ja	/iá/	Formal: "Ja", informal: "Jawoll" o "Genau"
            //en-GB	Inglés (Reino Unido)	Yes	/iés/	También "Aye" (Escocia), "Yeah" (informal)
            //es-ES	Español (España)	Sí	/sí/	Con acento diacrítico (diferencia de "si" condicional)
            //fr-FR	Francés (Francia)	Oui	/uí/	"Si" se usa para contradecir una negación
            //it-IT	Italiano (Italia)	Sì	/sí/	Con acento grave (importante para escritura correcta)
            //pl-PL	Polaco (Polonia)	Tak	/ták/	Gestual: movimiento vertical de cabeza
            //pt-PT	Portugués (Portugal)	Sim	/sĩ/ (nasal)	"É" o "isso" en contextos informales
            //zh-CN	Chino Mandarín (China)	是 (Shì)	/shr/ (tono desc
            List<String> yesList = Arrays.asList(new String[]{"ja", "yes", "si", "sí", "oui", "sì", "tak", "sim", "是"});
            return value != null && yesList.contains(value.toLowerCase());
        }
    }
}
