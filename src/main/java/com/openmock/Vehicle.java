package com.openmock;

import com.openmock.dto.VehicleDTO;
import com.openmock.dto.VehicleFilter;
import com.openmock.util.DateUtil;
import com.openmock.util.RndUtil;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsefa.IOFactoryException;

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
    private static List<List<VehicleDTO>> brandVehicles;

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
    /// @throws OpenMockException If is not possible to load the brands information
    public Vehicle() throws OpenMockException {
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
    /// @throws OpenMockException If is not possible to load the brands information
    public Vehicle(Locale locale) throws OpenMockException {
        this(locale,
                DateUtil.nowYearMonthXYearsAgo(10),
                DateUtil.nowYearMonth(),
                defaultBrands);
    }

    public Vehicle(Locale locale, YearMonth fromDateFilter, YearMonth toDateFilter, Brand... brands) throws OpenMockException {
        super(locale);
        this.fromDateFilter = fromDateFilter;
        this.toDateFilter = toDateFilter;
        selectedBrands = brands;
        initDictionaries();

        if(brandVehicles != null && !brandVehicles.isEmpty()){
            int indexBrand = RndUtil.getInstance().nextIntInRange(0, brandVehicles.size() - 1);
            List<VehicleDTO> cars = brandVehicles.get(indexBrand);

            int numCars = cars.size();
            int indexCar = RndUtil.getInstance().nextIntInRange(0, numCars - 1);
            VehicleDTO selectedCar = cars.get(indexCar);

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
    private void initDictionaries() throws OpenMockException {
        //Intentional double checking brandVehicles initialization
        if(brandVehicles == null) {
            synchronized (this) {
                if(brandVehicles == null) {
                    brandVehicles = new LinkedList<>();
                    try {
                        List<VehicleDTO> cars;
                        for(Brand brand: selectedBrands){
                            log.info(brand);
                            cars = (List<VehicleDTO>) loadCSV("vehicles/" + brand.toString(), VehicleDTO.class, true);
                            cars = VehicleFilter.filterCarsByManufactureDate(cars, fromDateFilter, toDateFilter);
                            if(cars != null && !cars.isEmpty()){
                                brandVehicles.add(cars);
                            }
                        }
                    } catch (FileNotFoundException | IOFactoryException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }
}
