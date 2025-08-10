package com.openmock;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Amenity {
    AUDIO("Audio"),
    VIDEO("Video"),
    AC_POWER("AC Power"),
    INTERNET("Internet"),
    FOOD("Food"),
    INFANTS("Infants");

    public final String label;

    Amenity(String label) {
        this.label = label;
    }

    @JsonCreator
    public static Amenity fromLabel(String label) {
        for (Amenity e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
