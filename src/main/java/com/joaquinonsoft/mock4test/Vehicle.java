package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.dto.Car;
import com.joaquinonsoft.mock4test.dto.CarFilter;
import com.joaquinonsoft.mock4test.util.DateUtil;
import com.joaquinonsoft.mock4test.util.RndUtil;
import lombok.AccessLevel;
import lombok.Getter;
import org.jsefa.IOFactoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Getter
public class Vehicle extends Field {
    @Getter(AccessLevel.NONE)
    private final static Logger log = LogManager.getLogger(Vehicle.class);

    @Getter(AccessLevel.NONE)
    private YearMonth fromDateFilter;
    @Getter(AccessLevel.NONE)
    private YearMonth toDateFilter;

    private String brandName;
    private String familyName;
    private String modelName;
    private YearMonth manufacturedFrom;
    private YearMonth manufacturedTo;
    private String typeName;
    private String typeFullName;
    private String energy;

    @Getter(AccessLevel.NONE)
    private static final Brand[] defaultBrands = new Brand[]{
            Brand.AUDI, Brand.BMW, Brand.CITROEN,
            Brand.DACIA, Brand.FIAT, Brand.FORD,
            Brand.MERCEDES_BENZ, Brand.OPEL, Brand.PEUGEOT,
            Brand.RENAULT, Brand.TOYOTA, Brand.VOLKSWAGEN
    };
    
    @Getter(AccessLevel.NONE)
    private volatile static Brand[] selectedBrands;

    @Getter(AccessLevel.NONE)
    private static List<List<Car>> brandVehicles;

    /// Create a random vehicle, in **Spanish**, manufactured in the last 10 years from
    /// one of the following brands (the most commons in Europe):
    ///  - AUDI
    ///  - BMW
    ///  - CITROËN
    ///  - DACIA
    ///  - FIAT
    ///  - FORD
    ///  - MERCEDES_BENZ
    ///  - OPEL
    ///  - PEUGEOT
    ///  - RENAULT
    ///  - TOYOTA
    ///  - VOLKSWAGEN
    /// @throws Mock4TestException If is not possible to load the brands information
    public Vehicle() throws Mock4TestException {
        this(Locale.forLanguageTag("es-ES"));
    }

    /// Create a random vehicle manufactured in the last 10 years from
    /// one of the following brands (the most commons in Europe):
    ///  - AUDI
    ///  - BMW
    ///  - CITROËN
    ///  - DACIA
    ///  - FIAT
    ///  - FORD
    ///  - MERCEDES_BENZ
    ///  - OPEL
    ///  - PEUGEOT
    ///  - RENAULT
    ///  - TOYOTA
    ///  - VOLKSWAGEN
    /// @param locale Language locale to be use to retrieve the information
    /// @throws Mock4TestException If is not possible to load the brands information
    public Vehicle(Locale locale) throws Mock4TestException {
        this(locale,
                DateUtil.nowYearMonthXYearsAgo(10),
                DateUtil.nowYearMonth(),
                defaultBrands);
    }

    public Vehicle(Locale locale, YearMonth fromDateFilter, YearMonth toDateFilter, Brand... brands) throws Mock4TestException {
        super(locale);
        this.fromDateFilter = fromDateFilter;
        this.toDateFilter = toDateFilter;
        selectedBrands = brands;
        initDictionaries();

        if(brandVehicles != null && !brandVehicles.isEmpty()){
            int indexBrand = RndUtil.getInstance().nextIntInRange(0, brandVehicles.size() - 1);
            List<Car> cars = brandVehicles.get(indexBrand);

            int numCars = cars.size();
            int indexCar = RndUtil.getInstance().nextIntInRange(0, numCars - 1);
            Car selectedCar = cars.get(indexCar);

            brandName = selectedCar.getBrandName();
            familyName = selectedCar.getFamilyName();
            modelName = selectedCar.getModelName();
            manufacturedFrom = selectedCar.getManufacturedFrom();
            manufacturedTo = selectedCar.getManufacturedTo();
            typeName = selectedCar.getTypeName();
            typeFullName = selectedCar.getTypeFullName();
            energy = selectedCar.getEnergy();
        }
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws Mock4TestException {
        if(brandVehicles == null) {
            synchronized (this) {
                if(brandVehicles == null) {
                    brandVehicles = new LinkedList<>();
                    try {
                        List<Car> cars;
                        for(Brand brand: selectedBrands){
                            log.info(brand);
                            cars = (List<Car>) loadCSV("vehicles/" + brand.toString(), Car.class);
                            cars = CarFilter.filterCarsByManufactureDate(cars, fromDateFilter, toDateFilter);
                            if(cars != null && !cars.isEmpty()){
                                brandVehicles.add(cars);
                            }
                        }
                    } catch (FileNotFoundException | IOFactoryException e) {
                        throw new Mock4TestException(e);
                    }
                }
            }
        }
    }
}
