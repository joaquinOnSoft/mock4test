package com.joaquinonsoft.mock4test.dto;

import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;

import java.time.LocalDate;
import java.util.Date;

/**
 * DTO describing a car (brand, family, model, type).
 */
@Getter
@Setter
@CsvDataType()
public class Car {
    private String brandId;
    private String brandName;
    private String familyId;
    private String familyName;
    private String modelId;
    private String modelName;
    @CsvDate(value = "MM/yyyy")
    private LocalDate manufacturedFrom;
    @CsvDate(value = "MM/yyyy")
    private LocalDate manufacturedTo;
    private String typeId;
    private String typeName;
    private String typeFullName;
    private String energy;
}
