package com.openmock.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;

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
