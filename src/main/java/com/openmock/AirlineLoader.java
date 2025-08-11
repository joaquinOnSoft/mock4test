package com.openmock;

import com.openmock.util.RndUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AirlineLoader extends AbstractLoader {
    private static final AirlineName[] defaultAirlines = new AirlineName[]{
            AirlineName.AER_LINGUS, AirlineName.AEROFLOT, AirlineName.AIR_BALTIC,
            AirlineName.AIR_BELGIUM, AirlineName.AIR_CORSICA, AirlineName.AIR_DOLOMITI,
            AirlineName.AIR_FRANCE, AirlineName.AIR_EUROPA, AirlineName.AIR_MALTA,
            AirlineName.AIR_SERBIA, AirlineName.ALITALIA, AirlineName. AZORES_AIRLINES,
            AirlineName.BRITISH_AIRWAYS, AirlineName.BRUSSELS_AIRLINES, AirlineName.CROATIA_AIRLINES,
            AirlineName.CZECH_AIRLINES, AirlineName.EASYJET, AirlineName.FINNAIR,
            AirlineName.FRENCH_BEE, AirlineName.HELVETIC_AIRWAYS, AirlineName.IBERIA,
            AirlineName.ICELANDAIR, AirlineName.KLM, AirlineName.LUFTHANSA,
            AirlineName.LUXAIR, AirlineName.NORDWIND_AIRLINES, AirlineName.NORWEGIAN_AIR_INTERNATIONAL,
            AirlineName.SWISS, AirlineName.TAP_PORTUGAL
    };
    private static final Log log = LogFactory.getLog(AirlineLoader.class);

    private static AirlineName[] selectedAirlines;
    private volatile List<Airline> airlines;


    public AirlineLoader() throws OpenMockException {
        this(Locale.forLanguageTag(LANG_TAG_ES_ES), defaultAirlines);
    }


    public AirlineLoader(Locale locale, AirlineName[] selectedAirlines) throws OpenMockException {
        this.locale = locale;
        this.selectedAirlines = selectedAirlines;
        initDictionaries();
    }

    private void initDictionaries() throws OpenMockException {
        if (airlines == null) {
            synchronized (this) {
                if (airlines == null) {
                    airlines = new LinkedList<>();

                    try {
                        Airline airline;
                        for(AirlineName airName: selectedAirlines) {
                            airline = loadJSON("airline/" + airName.getLabel(), Airline.class);
                            if(airline != null) {
                                airlines.add(airline);
                            }
                        }
                    } catch (IOException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }

    public Airline getAirline(){
        Airline airline = null;

        if(airlines != null && !airlines.isEmpty()){
            airline = airlines.get(RndUtil.getInstance().nextIntInRange(0, airlines.size() -1));
        }
        return airline;
    }
}
