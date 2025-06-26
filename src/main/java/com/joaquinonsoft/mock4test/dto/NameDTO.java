package com.joaquinonsoft.mock4test.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 * DTO describing a person's name.
 */
@Getter
@Setter
@CsvDataType
public class NameDTO {
    @CsvBindByName
    private String name;
}
