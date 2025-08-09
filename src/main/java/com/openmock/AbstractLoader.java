package com.openmock;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.openmock.util.FileUtil;

import java.io.*;
import java.util.List;
import java.util.Locale;

public abstract class AbstractLoader {
    protected static final String LANG_TAG_ES_ES = "es-ES";
    protected static final String LANG_ES = "es";

    private static final char COMMA = ',';
    private static final char QUOTE = '"';

    protected Locale locale;

    @SuppressWarnings("unchecked")
    protected List<?> loadCSV(String alias, Class<?> pojo, boolean withQuoteChar) throws FileNotFoundException {
        File csv = FileUtil.getLocalizedCSVFromResources(locale, alias);
        Reader reader = new BufferedReader(new FileReader(csv));

        CsvToBean<?> csvReader;

        if (withQuoteChar) {
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(pojo)
                    .withSeparator(COMMA)
                    .withQuoteChar(QUOTE)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

        } else {
            csvReader = new CsvToBeanBuilder(reader)
                    .withType(pojo)
                    .withSeparator(COMMA)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
        }

        return csvReader.parse();
    }
}
