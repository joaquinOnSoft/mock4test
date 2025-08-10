package com.openmock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AirlineLoader extends AbstractLoader {
    private List<Airline> airlines;

    public AirlineLoader() throws OpenMockException {
        this(Locale.forLanguageTag(LANG_TAG_ES_ES));
    }


    public AirlineLoader(Locale locale) throws OpenMockException {
        this.locale = locale;
        initDictionaries();
    }

    private void initDictionaries() throws OpenMockException {
        if (airlines == null) {
            synchronized (this) {
                if (airlines == null) {
                    try {
                        Airline airline = (Airline) loadJSON("airlien/Aegean-Airlines", Airline.class);
                    } catch (IOException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }
}
