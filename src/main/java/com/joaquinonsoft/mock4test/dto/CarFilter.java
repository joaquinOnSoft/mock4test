package com.joaquinonsoft.mock4test.dto;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarFilter {

    /**
     * Filters a list of Car objects to include only those manufactured within a specified date range.
     * The filter considers cars whose 'manufacturedFrom' date is before or equal to the 'endDate',
     * and whose 'manufacturedTo' date is after or equal to the 'startDate'.
     *
     * @param cars The list of Car objects to filter.
     * @param startDate The start date of the manufacturing range (inclusive).
     * @param endDate The end date of the manufacturing range (inclusive).
     * @return A new List<Car> containing only the cars that fall within the specified manufacturing date range.
     */
    public static List<Car> filterCarsByManufactureDate(List<Car> cars, YearMonth startDate, YearMonth endDate) {
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