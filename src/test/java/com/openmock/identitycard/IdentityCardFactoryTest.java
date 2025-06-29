package com.openmock.identitycard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IdentityCardFactoryTest {


    @Test
    void getIdentityCardWithSpanishLocaleReturnsSpanishIdentityCard() {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(Locale.forLanguageTag("es-ES"));
        assertNotNull(card);
        assertInstanceOf(SpanishIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithFrenchLocaleReturnsFrenchIdentityCard() {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(Locale.forLanguageTag("fr-FR"));
        assertNotNull(card);
        assertInstanceOf(FrenchIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithItalianLocaleReturnsItalianIdentityCard() {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(Locale.forLanguageTag("it-IT"));
        assertNotNull(card);
        assertInstanceOf(ItalianIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithGermanLocaleReturnsGermanIdentityCard() {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(Locale.forLanguageTag("de-DE"));
        assertNotNull(card);
        assertInstanceOf(GermanIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithPortugueseLocaleReturnsPortugueseIdentityCard() {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(Locale.forLanguageTag("pt-PT"));
        assertNotNull(card);
        assertInstanceOf(PortugueseIdentityCard.class, card);
    }

    @ParameterizedTest
    @MethodSource("provideEuropeanLocales")
    void getIdentityCardWithEuropeanLocaleReturnsCorrectImplementation(Locale locale, Class<?> expectedClass) {
        IIdentityCard card = IdentityCardFactory.getIdentityCard(locale);
        assertNotNull(card);
        assertTrue(expectedClass.isInstance(card));
    }

    @Test
    void getIdentityCardWithUnsupportedLocaleThrowsIllegalArgumentException() {
        Locale unsupportedLocale = Locale.forLanguageTag("en-US");
        assertThrows(IllegalArgumentException.class, () -> IdentityCardFactory.getIdentityCard(unsupportedLocale));
    }

    // Tests similares para otros países...

    private static Stream<Object[]> provideEuropeanLocales() {
        return Stream.of(
                new Object[]{Locale.forLanguageTag("de-AT"), AustrianIdentityCard.class},
                new Object[]{Locale.forLanguageTag("en-GB"), BritishIdentityCard.class},
                new Object[]{Locale.forLanguageTag("en-CA"), CanadianIdentityCard.class},
                new Object[]{Locale.forLanguageTag("zh-CN"), ChineseIdentityCard.class},
                new Object[]{Locale.forLanguageTag("fr-FR"), FrenchIdentityCard.class},
                new Object[]{Locale.forLanguageTag("de-DE"), GermanIdentityCard.class},
                new Object[]{Locale.forLanguageTag("en-IE"), IrishIdentityCard.class},
                new Object[]{Locale.forLanguageTag("it-IT"), ItalianIdentityCard.class},
                new Object[]{Locale.forLanguageTag("ja-JP"), JapaneseIdentityCard.class},
                new Object[]{Locale.forLanguageTag("pl-PL"), PolishIdentityCard.class},
                new Object[]{Locale.forLanguageTag("pt-PT"), PortugueseIdentityCard.class},
                new Object[]{Locale.forLanguageTag("es-ES"), SpanishIdentityCard.class}
                //new Object[]{Locale.forLanguageTag("ro-RO"), RomanianIdentityCard.class}

        );
    }
}