package com.openmock;

import com.openmock.dto.ElementDTO;
import com.openmock.util.RndUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class Element extends Field{
    @Getter(AccessLevel.NONE)
    private final static Logger log = LogManager.getLogger(Element.class);
    @Getter(AccessLevel.NONE)
    private volatile static List<ElementDTO> elements;

    private int atomicNumber;
    private String name;
    private String symbol;
    private float atomicMass;
    private int numberOfNeutrons;
    private int numberOfProtons;
    private int numberOfElectrons;
    private int period;
    private int group;
    private String phase;
    private boolean radioactive;
    private boolean natural;
    private boolean metal;
    private boolean nonmetal;
    private boolean metalloid;
    private String Type;
    private float atomicRadius;
    private float electronegativity;
    private float  firstIonization;
    private double density;
    private float meltingPoint;
    private float boilingPoint;
    private int numberOfIsotopes;
    private String discoverer;
    private int year;
    private float specificHeat;
    private int numberOfShells;
    private int numberOfValence;

    public Element() throws OpenMockException {
        this(Locale.forLanguageTag(LANG_TAG_ES_ES));
    }

    public Element(Locale locale) throws OpenMockException {
        super(locale);

        initDictionaries();

        if(elements != null){
            int numElements = elements.size() - 1;
            int index = RndUtil.getInstance().nextIntInRange(0, numElements);

            ElementDTO element = elements.get(index);

            atomicNumber = element.getAtomicNumber();
            name = element.getName();
            symbol = element.getSymbol();
            atomicMass = element.getAtomicMass();
            numberOfNeutrons = element.getNumberOfNeutrons();
            numberOfProtons = element.getNumberOfProtons();
            numberOfElectrons = element.getNumberOfElectrons();
            period = element.getPeriod();
            group = element.getGroup();
            phase = element.getPhase();
            radioactive = element.isRadioactive();
            natural = element.isNatural();
            metal = element.isMetal();
            nonmetal = element.isNonmetal();
            metalloid = element.isMetalloid();
            Type = element.getType();
            atomicRadius = element.getAtomicRadius();
            electronegativity = element.getElectronegativity();
            firstIonization = element.getFirstIonization();
            density = element.getDensity();
            meltingPoint = element.getMeltingPoint();
            boilingPoint = element.getBoilingPoint();
            numberOfIsotopes = element.getNumberOfIsotopes();
            discoverer = element.getDiscoverer();
            year = element.getYear();
            specificHeat = element.getSpecificHeat();
            numberOfShells = element.getNumberOfShells();
            numberOfValence = element.getNumberOfValence();
        }
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws OpenMockException {
        if(elements == null) {
            synchronized (this) {
                if(elements == null) {
                    try {
                        elements = (List<ElementDTO>) loadCSV("element/periodic-of-elements", ElementDTO.class);
                    } catch (FileNotFoundException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }
}
