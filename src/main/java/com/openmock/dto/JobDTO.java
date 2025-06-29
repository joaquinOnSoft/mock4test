package com.openmock.dto;

import lombok.Getter;
import lombok.Setter;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 * DTO describing a person's job.
 */
@Getter
@Setter
@CsvDataType()
public class JobDTO {
    @CsvField(pos = 1)
    private String jobArea;

    @CsvField(pos = 2)
    private String jobDescriptor;

    @CsvField(pos = 3)
    private String jobTitle;

    @CsvField(pos = 4)
    private String jobType;
}
