package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.util.FileUtil;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvDeserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Field {
    protected Locale locale;

    /**
     * Use "es-ES" as default language and region tag.
     */
    public Field(){
        this.locale = Locale.forLanguageTag("es-ES");
    }

    public Field(Locale locale){
        this.locale = locale;
    }

    protected List<?> loadCSV(String alias, Class<?> dto) throws FileNotFoundException {
        List<?> dtos = new ArrayList<>();

        CsvConfiguration config = new CsvConfiguration();
        // header of size 1, no footer, store the filtered lines
        config.setLineFilter(new HeaderAndFooterFilter(1, false, true));
        CsvDeserializer deserializer = CsvIOFactory.createFactory(config, dto).createDeserializer();
        File csv = FileUtil.getLocalizedCSVFromResources(locale, alias);
        if(csv != null) {
            deserializer.open(new FileReader(csv));
            while (deserializer.hasNext()) {
                dtos.add(deserializer.next());
            }
            //String header = deserializer.getStoredLines().get(0).getContent();
            deserializer.close(true);
        }
        return dtos;
    }
}
