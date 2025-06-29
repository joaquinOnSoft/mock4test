package com.openmock;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.fail;

public class ElementTest {
    @RepeatedTest(100)
    public void elementSpanish(){
        try {
            Element element = new Element();
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
    public void elementSpanish(String langTag){
        try {
            Element element = new Element(Locale.forLanguageTag(langTag));
        } catch (OpenMockException e) {
            fail(e);
        }

    }
}
