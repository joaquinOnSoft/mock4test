package com.openmock.dto;

import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import java.time.YearMonth;

/**
 * DTO describing a car (brand, family, model, type).
 */
@Getter
@Setter
@CsvDataType()
public class VehicleDTO {
    @CsvField(pos = 1)
    private String brandId;
    @CsvField(pos = 2)
    private String brandName;
    @CsvField(pos = 3)
    private String familyId;
    @CsvField(pos = 4)
    private String familyName;
    @CsvField(pos = 5)
    private String modelId;
    @CsvField(pos = 6)
    private String modelName;
    @CsvField(pos = 7)
    @CsvDate("MM/yyyy")
    private YearMonth manufacturedFrom;
    @CsvField(pos = 8)
    @CsvDate(value = "MM/yyyy")
    private YearMonth manufacturedTo;
    @CsvField(pos = 9)
    private String typeId;
    @CsvField(pos = 10)
    private String typeName;
    @CsvField(pos = 11)
    private String typeFullName;
    @CsvField(pos = 12)
    private String energy;
}
