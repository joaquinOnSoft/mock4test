package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.util.FileUtil;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class Field {
    protected Locale locale;

    /**
     * Use "es-ES" as default language and region tag.
     */
    public Field() {
        this.locale = Locale.forLanguageTag("es-ES");
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
        if(withQuoteChar){
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
        }
        else{
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
        }
        return csvReader.parse();
    }
}
