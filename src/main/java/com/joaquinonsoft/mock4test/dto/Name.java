package com.joaquinonsoft.mock4test.dto;

import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 * DTO describing a person's name.
 */
@Getter
@Setter
@CsvDataType()
public class Name {
    @CsvField(pos = 1)
    private String name;
}
