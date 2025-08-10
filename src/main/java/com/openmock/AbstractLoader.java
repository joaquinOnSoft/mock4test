package com.openmock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.openmock.util.FileUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    protected <T> T loadJSON(String alias, Class<T> pojo) throws IOException {
        File jsonFile = FileUtil.getLocalizedJSONFromResources(locale, alias);
        String jsonStr = Files.readString(jsonFile.toPath(), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, pojo);
    }
}
