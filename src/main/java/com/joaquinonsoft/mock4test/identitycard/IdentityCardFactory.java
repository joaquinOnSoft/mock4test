package com.joaquinonsoft.mock4test.identitycard;

import java.util.Locale;

public class IdentityCardFactory {
    public IIdentityCard getIdentityCard(Locale locale) {
        String countryCode = locale.getCountry().toUpperCase();

        switch (countryCode) {
            case "ES": return new SpanishIdentityCard();
            case "FR": return new FrenchIdentityCard();
            case "IT": return new ItalianIdentityCard();
            case "DE": return new GermanIdentityCard();
            case "PT": return new PortugueseIdentityCard();
            /*
            case "BE": return new BelgianIdentityCard();
            case "NL": return new DutchIdentityCard();
            case "SE": return new SwedishIdentityCard();
            case "FI": return new FinnishIdentityCard();
            case "DK": return new DanishIdentityCard();
            case "NO": return new NorwegianIdentityCard();
            case "AT": return new AustrianIdentityCard();
            case "CH": return new SwissIdentityCard();
            case "IE": return new IrishIdentityCard();
            case "PL": return new PolishIdentityCard();
            case "CZ": return new CzechIdentityCard();
            case "SK": return new SlovakIdentityCard();
            case "HU": return new HungarianIdentityCard();
            case "RO": return new RomanianIdentityCard();
            case "BG": return new BulgarianIdentityCard();
            case "GR": return new GreekIdentityCard();
            case "HR": return new CroatianIdentityCard();
            case "SI": return new SlovenianIdentityCard();
            case "EE": return new EstonianIdentityCard();
            case "LV": return new LatvianIdentityCard();
            case "LT": return new LithuanianIdentityCard();
            case "CY": return new CypriotIdentityCard();
            case "LU": return new LuxembourgishIdentityCard();
            case "MT": return new MalteseIdentityCard();
            case "IS": return new IcelandicIdentityCard();
            case "LI": return new LiechtensteinIdentityCard();
            */
            // Añadir más países según sea necesario
            default: throw new IllegalArgumentException("No identity card implementation for country: " + countryCode);
        }
    }
}