package com.openmock;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.openmock.util.FileUtil;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class Field {
    protected static final String LANG_TAG_ES_ES = "es-ES";
    protected static final String LANG_ES = "es";

    private static final char COMMA = ',';
    private static final char QUOTE = '"';

    protected Locale locale;

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

        if(withQuoteChar){
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(COMMA)
                    .withQuoteChar(QUOTE)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

        }
        else{
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(COMMA)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
        }

        return csvReader.parse();
    }
}
