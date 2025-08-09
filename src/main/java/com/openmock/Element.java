package com.openmock;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/// A chemical element is a chemical substance whose atoms all have the same number of protons.
/// The number of protons is called the atomic number of that element. For example, oxygen has
/// an atomic number of 8: each oxygen atom has 8 protons in its nucleus. Atoms of the same
/// element can have different numbers of neutrons in their nuclei, known as isotopes of the element.
/// Two or more atoms can combine to form molecules. Some elements form molecules of atoms of said
/// element only: e.g. atoms of hydrogen (H) form diatomic molecules (H2). Chemical compounds are
/// substances made of atoms of different elements; they can have molecular or non-molecular
/// structure. Mixtures are materials containing different chemical substances; that means
/// (in case of molecular substances) that they contain different types of molecules.
/// Atoms of one element can be transformed into atoms of a different element in nuclear reactions,
/// which change an atom's atomic number.
@Getter
@Setter
public class Element extends Field{
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
    @CsvCustomBindByName(column="Radioactive", converter= Element.YesNo2BooleanConverter.class)
    private boolean radioactive;
    //Natural: yes/(empty)
    @CsvCustomBindByName(column="Natural", converter= Element.YesNo2BooleanConverter.class)
    private boolean natural;
    //Metal: yes/(empty)
    @CsvCustomBindByName(column="Metal", converter= Element.YesNo2BooleanConverter.class)
    private boolean metal;
    @CsvBindByName
    //Non-metal: yes/(empty)
    @CsvCustomBindByName(column="Nonmetal", converter= Element.YesNo2BooleanConverter.class)
    private boolean nonmetal;
    @CsvBindByName
    //Metalloid: yes/(empty)
    @CsvCustomBindByName(column="Metalloid", converter= Element.YesNo2BooleanConverter.class)
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

        //de-DE	Ja	/iá/
        //en-GB	Yes	/iés/
        //es-ES	Sí	/sí/
        //fr-FR	Oui	/uí/
        //it-IT	Sì	/sí/
        //pl-PL	Tak	/ták/
        //pt-PT	Sim	/sĩ/
        //zh-CN	是 (Shì)
        private static final List<String> yesList = Arrays.asList(
                "ja", "yes", "si",
                "sí", "oui", "sì",
                "tak", "sim", "是");

        @Override
        protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
            return value != null && yesList.contains(value.toLowerCase());
        }
    }
}
