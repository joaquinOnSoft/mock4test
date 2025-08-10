package com.openmock;

import com.openmock.util.DateUtil;
import com.openmock.util.RndUtil;
import lombok.extern.log4j.Log4j2;
import org.jsefa.IOFactoryException;

import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
public class VehicleLoader extends AbstractLoader {
    private final YearMonth fromDateFilter;
    private final YearMonth toDateFilter;

    private static final Brand[] defaultBrands = new Brand[]{
            Brand.AUDI, Brand.BMW, Brand.CITROEN,
            Brand.DACIA, Brand.FIAT, Brand.FORD,
            Brand.MERCEDES_BENZ, Brand.OPEL, Brand.PEUGEOT,
            Brand.RENAULT, Brand.TOYOTA, Brand.VOLKSWAGEN
    };

    private volatile static Brand[] selectedBrands;

    private static List<List<Vehicle>> brandVehicles;


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
    ///
    /// @throws OpenMockException If is not possible to load the brands information
    public VehicleLoader() throws OpenMockException {
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
    ///
    /// @param locale Language locale to be used to retrieve the information
    /// @throws OpenMockException If is not possible to load the brands information
    public VehicleLoader(Locale locale) throws OpenMockException {
        this(locale,
                DateUtil.nowYearMonthXYearsAgo(10),
                DateUtil.nowYearMonth(),
                defaultBrands);
    }

    public VehicleLoader(Locale locale, YearMonth fromDateFilter, YearMonth toDateFilter, Brand... brands) throws OpenMockException {
        this.locale = locale;
        this.fromDateFilter = fromDateFilter;
        this.toDateFilter = toDateFilter;
        selectedBrands = brands;
        initDictionaries();
    }

    public Vehicle getVehicle() {
        Vehicle selectedCar = null;
        if (brandVehicles != null && !brandVehicles.isEmpty()) {
            int indexBrand = RndUtil.getInstance().nextIntInRange(0, brandVehicles.size() - 1);
            List<Vehicle> cars = brandVehicles.get(indexBrand);

            int numCars = cars.size();
            int indexCar = RndUtil.getInstance().nextIntInRange(0, numCars - 1);
            selectedCar = cars.get(indexCar);
        }
        return selectedCar;
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws OpenMockException {
        //Intentional double-checking brandVehicles initialization
        if (brandVehicles == null) {
            synchronized (this) {
                if (brandVehicles == null) {
                    brandVehicles = new LinkedList<>();
                    try {
                        List<Vehicle> cars;
                        for (Brand brand : selectedBrands) {
                            log.info(brand);
                            cars = (List<Vehicle>) loadCSV("vehicles/" + brand.toString(), Vehicle.class, true);
                            cars = filterCarsByManufactureDate(cars, fromDateFilter, toDateFilter);
                            if (cars != null && !cars.isEmpty()) {
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

    /**
     * Filters a list of Car objects to include only those manufactured within a specified date range.
     * The filter considers cars whose 'manufacturedFrom' date is before or equal to the 'endDate',
     * and whose 'manufacturedTo' date is after or equal to the 'startDate'.
     *
     * @param cars      The list of Car objects to filter.
     * @param startDate The start date of the manufacturing range (inclusive).
     * @param endDate   The end date of the manufacturing range (inclusive).
     * @return A new List<Car> containing only the cars that fall within the specified manufacturing date range.
     */
    public List<Vehicle> filterCarsByManufactureDate(List<Vehicle> cars, YearMonth startDate, YearMonth endDate) {
        if (cars == null || cars.isEmpty()) {
            return List.of(); // Return an empty list if the input list is null or empty
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        return cars.stream()
                .filter(Objects::nonNull)
                .filter(car -> {
                    // Check if the car's manufacturedFrom date is before or on the endDate
                    // AND if the car's manufacturedTo date is after or on the startDate
                    return (car.getManufacturedFrom() != null && !car.getManufacturedFrom().isAfter(endDate)) &&
                            (car.getManufacturedTo() != null && !car.getManufacturedTo().isBefore(startDate));
                })
                .collect(Collectors.toList());
    }
}
