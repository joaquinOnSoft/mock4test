package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.util.FileUtil;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;
import java.util.Locale;

public class Field {
    public static final String LANG_TAG_ES_ES = "es-ES";
    public static final String LANG_ES = "es";

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

        ;
        if(withQuoteChar){
            CsvToBean<?> csvReader = new CsvToBeanBuilder(reader)
                    .withType(dto)
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvReader.parse();
        }
        else{
            CSVReader csvReader = new CSVReader(new FileReader(csv));
            CsvToBean<?> csv2Bean = new CsvToBean();
            csv2Bean.setCsvReader(csvReader);
            //csv2Beand.setMappingStrategy(setColumMapping());

            return csv2Bean.parse();
        }

    }
}
