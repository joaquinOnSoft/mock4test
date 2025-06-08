package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.util.stream.Stream;

class IdentityCardFactoryTest {

    private final IdentityCardFactory factory = new IdentityCardFactory();

    @Test
    void getIdentityCardWithSpanishLocaleReturnsSpanishIdentityCard() {
        IIdentityCard card = factory.getIdentityCard(Locale.forLanguageTag("es-ES"));
        assertNotNull(card);
        assertInstanceOf(SpanishIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithFrenchLocaleReturnsFrenchIdentityCard() {
        IIdentityCard card = factory.getIdentityCard(Locale.forLanguageTag("fr-FR"));
        assertNotNull(card);
        assertInstanceOf(FrenchIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithItalianLocaleReturnsItalianIdentityCard() {
        IIdentityCard card = factory.getIdentityCard(Locale.forLanguageTag("it-IT"));
        assertNotNull(card);
        assertInstanceOf(ItalianIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithGermanLocaleReturnsGermanIdentityCard() {
        IIdentityCard card = factory.getIdentityCard(Locale.forLanguageTag("de-DE"));
        assertNotNull(card);
        assertInstanceOf(GermanIdentityCard.class, card);
    }

    @Test
    void getIdentityCardWithPortugueseLocaleReturnsPortugueseIdentityCard() {
        IIdentityCard card = factory.getIdentityCard(Locale.forLanguageTag("pt-PT"));
        assertNotNull(card);
        assertInstanceOf(PortugueseIdentityCard.class, card);
    }

    @ParameterizedTest
    @MethodSource("provideEuropeanLocales")
    void getIdentityCardWithEuropeanLocaleReturnsCorrectImplementation(Locale locale, Class<?> expectedClass) {
        IIdentityCard card = factory.getIdentityCard(locale);
        assertNotNull(card);
        assertTrue(expectedClass.isInstance(card));
    }

    @Test
    void getIdentityCardWithUnsupportedLocaleThrowsIllegalArgumentException() {
        Locale unsupportedLocale = Locale.forLanguageTag("en-US");
        assertThrows(IllegalArgumentException.class, () -> factory.getIdentityCard(unsupportedLocale));
    }

    // Tests similares para otros países...

    private static Stream<Object[]> provideEuropeanLocales() {
        return Stream.of(
                new Object[]{Locale.forLanguageTag("es-ES"), SpanishIdentityCard.class},
                new Object[]{Locale.forLanguageTag("fr-FR"), FrenchIdentityCard.class},
                new Object[]{Locale.forLanguageTag("it-IT"), ItalianIdentityCard.class},
                new Object[]{Locale.forLanguageTag("de-DE"), GermanIdentityCard.class},
                new Object[]{Locale.forLanguageTag("pt-PT"), PortugueseIdentityCard.class}
                //new Object[]{new Locale("be", "BE"), BelgianIdentityCard.class},
                //new Object[]{new Locale("nl", "NL"), DutchIdentityCard.class},
                //new Object[]{new Locale("se", "SE"), SwedishIdentityCard.class}
                // Añadir más países según sea necesario
        );
    }
}