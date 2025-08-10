package com.openmock;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ElementLoaderTest {
    private static final int HYDROGEN_ATOMIC_NUM = 1;
    private static final int OGANESSON_ATOMIC_NUM = 118;
    private static final List<String> radioactiveElements = Arrays.asList(
            "Ac", "Am", "At", "Bh", "Bi", "Bk", "Cf", "Cm", "Cn", "Db",
            "Ds", "Es", "Fl", "Fm", "Fr", "Hs", "Lr", "Lv", "Mc", "Md",
            "Mt", "Nh", "No", "Np", "Pa", "Po", "Pm", "Pu", "Ra", "Rf",
            "Rg", "Rn", "Sg", "Tc", "Th", "Ts", "U");

    private static void validateElement(Element element) {
        assertTrue(element.getAtomicNumber() >= HYDROGEN_ATOMIC_NUM);
        assertTrue(element.getAtomicNumber() <= OGANESSON_ATOMIC_NUM);

        String symbol = element.getSymbol();
        assertNotNull(symbol);
        assertTrue(symbol.length() <= 2);
        assertTrue(Character.isUpperCase(symbol.charAt(0)));
        if (symbol.length() == 2) {
            assertTrue(Character.isLowerCase(symbol.charAt(1)));
        }

        if (element.isRadioactive()) {
            assertTrue(radioactiveElements.contains(symbol));
        }
    }

    @RepeatedTest(500)
    public void elementSpanish() {
        try {
            ElementLoader loader = new ElementLoader();
            validateElement(loader.getElement());
        } catch (OpenMockException e) {
            fail(e);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "de-DE",
            "en-GB",
            "es-ES",
            "fr-FR",
            "it-IT",
            "pl-PL",
            "pt-PT",
            "zh-CN"
    })
    public void elementSpanish(String langTag) {
        try {
            ElementLoader loader = new ElementLoader(Locale.forLanguageTag(langTag));
            validateElement(loader.getElement());
        } catch (OpenMockException e) {
            fail(e);
        }
    }

    @Test
    public void toJSON() {
        try {
            ElementLoader loader = new ElementLoader();
            String json = loader.getElement().toJSON();

            //System.out.println(json);
            assertNotNull(json);
            assertTrue(json.startsWith("{"));
        } catch (OpenMockException e) {
            fail(e);

        }
    }

    @Test
    public void toXML() {
        try {
            ElementLoader loader = new ElementLoader();
            String json = loader.getElement().toXML();

            //System.out.println(json);
            assertNotNull(json);
            assertTrue(json.startsWith("<Element>"));
        } catch (OpenMockException e) {
            fail(e);

        }
    }
}
