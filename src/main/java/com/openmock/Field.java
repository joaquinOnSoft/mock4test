package com.openmock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.openmock.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class Field {
    @JsonIgnore
    protected static final String LANG_TAG_ES_ES = "es-ES";
    @JsonIgnore
    protected static final String LANG_ES = "es";

    @JsonIgnore
    private static final char COMMA = ',';
    @JsonIgnore
    private static final char QUOTE = '"';

    @JsonIgnore
    protected Locale locale;

    @JsonIgnore
    protected static final Logger log = LogManager.getLogger(Field.class);


    /**
     * Use "es-ES" as default language and region tag.
     */
    public Field() {
        this.locale = Locale.forLanguageTag(LANG_TAG_ES_ES);
    }

    public Field(Locale locale) {
        this.locale = locale;
    }

    protected List<?> loadCSV(String alias, Class<?> dto) throws FileNotFoundException {
        return loadCSV(alias, dto, false);
    }

    @SuppressWarnings("unchecked")
    protected List<?> loadCSV(String alias, Class<?> dto, boolean withQuoteChar) throws FileNotFoundException {
        File csv = FileUtil.getLocalizedCSVFromResources(locale, alias);
        Reader reader = new BufferedReader(new FileReader(csv));

        CsvToBean<?> csvReader;

        if (withQuoteChar) {
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(COMMA)
                    .withQuoteChar(QUOTE)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

        } else {
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(COMMA)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
        }

        return csvReader.parse();
    }

    public String toJSON() {
        String json = null;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error generating JSON: ", e);
        }
        return json;
    }

    public String toXML() {
        String xml = null;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xml = xmlMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error generating JSON: ", e);
        }
        return xml;
    }
}
