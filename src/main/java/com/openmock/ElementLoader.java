package com.openmock;

import com.openmock.util.RndUtil;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

public class ElementLoader extends AbstractLoader {
    private volatile static List<Element> elements;

    public ElementLoader() throws OpenMockException {
        this(Locale.forLanguageTag(LANG_TAG_ES_ES));
    }


    public ElementLoader(Locale locale) throws OpenMockException {
        this.locale = locale;
        initDictionaries();
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws OpenMockException {
        if (elements == null) {
            synchronized (this) {
                if (elements == null) {
                    try {
                        elements = (List<Element>) loadCSV("element/periodic-of-elements", Element.class, false);
                    } catch (FileNotFoundException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }

    public Element getElement() {
        Element element = null;

        if (elements != null) {
            int numElements = elements.size() - 1;
            int index = RndUtil.getInstance().nextIntInRange(0, numElements);

            element = elements.get(index);
        }

        return element;
    }
}
