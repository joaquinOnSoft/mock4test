package com.joaquinonsoft.mock4test.identitycard;

import java.util.Locale;

public class IdentityCardFactory {
    public static IIdentityCard getIdentityCard(Locale locale) {
        String countryCode = locale.getCountry().toUpperCase();

        switch (countryCode) {
            case "AT": return new AustrianIdentityCard();
            case "CA": return new CanadianIdentityCard();
            case "CN": return new ChineseIdentityCard();
            case "DE": return new GermanIdentityCard();
            case "ES": return new SpanishIdentityCard();
            case "FR": return new FrenchIdentityCard();
            case "GB": return new BritishIdentityCard();
            case "IE": return new IrishIdentityCard();
            case "IT": return new ItalianIdentityCard();
            case "JP": return new JapaneseIdentityCard();
            case "PL": return new PolishIdentityCard();
            case "PT": return new PortugueseIdentityCard();
            //case "RO": return new RomanianIdentityCard();
            //case "BE": return new BelgianIdentityCard();
            //case "NL": return new DutchIdentityCard();
            //case "SE": return new SwedishIdentityCard();
            //case "FI": return new FinnishIdentityCard();
            //case "DK": return new DanishIdentityCard();
            //case "NO": return new NorwegianIdentityCard();
            //case "CH": return new SwissIdentityCard();
            //case "CZ": return new CzechIdentityCard();
            //case "SK": return new SlovakIdentityCard();
            //case "HU": return new HungarianIdentityCard();
            //case "BG": return new BulgarianIdentityCard();
            //case "GR": return new GreekIdentityCard();
            //case "HR": return new CroatianIdentityCard();
            //case "SI": return new SlovenianIdentityCard();
            //case "EE": return new EstonianIdentityCard();
            //case "LV": return new LatvianIdentityCard();
            //case "LT": return new LithuanianIdentityCard();
            //case "CY": return new CypriotIdentityCard();
            //case "LU": return new LuxembourgishIdentityCard();
            //case "MT": return new MalteseIdentityCard();
            //case "IS": return new IcelandicIdentityCard();
            //case "LI": return new LiechtensteinIdentityCard();
            default: throw new IllegalArgumentException("No identity card implementation for country: " + countryCode);
        }
    }
}