package com.joaquinonsoft.mock4test;

import java.time.LocalDate;

public enum Prefix {
    MISS("Miss"),
    MS("Ms."),
    MR("Mr.");

    public final String pref;

    private Prefix(String label) {
        this.pref = label;
    }

    public Prefix getPrefix(LocalDate birthdate, Sex sex){
        Prefix prefix = MR;

        if( sex == Sex.FEMALE){
            prefix = MS;
            if(birthdate  != null ) {
                if (!birthdate.isBefore(LocalDate.now().minusYears(18))) {
                    prefix = MISS;
                }
            }
        }

        return prefix;
    }
}
