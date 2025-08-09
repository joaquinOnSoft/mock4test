package com.openmock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Locale;

@Setter
@Getter
public class Person extends Field{
    private String bio;
    private String firstName;
    private String fullName;
    private String lastName;
    private String secondLastName;
    private String nationalIdentityCard;
    private String gender;
    private String jobArea;
    private String jobDescriptor;
    private String jobTitle;
    private String jobType;
    private String middleName;
    private Sex sex;
    private ZodiacSign zodiacSign;
    private LocalDate birthdate;

    public Person(){
        this(Locale.forLanguageTag(LANG_TAG_ES_ES));
    }

    public Person(Locale locale) {
        super(locale);
    }

    public String getFullName(){
        if(fullName == null) {
            StringBuilder fullN = new StringBuilder();

            fullN.append(firstName);
            if (middleName != null) {
                fullN.append(" ").append(middleName);
            }
            if (lastName != null) {
                fullN.append(" ").append(lastName);
            }
            if (secondLastName != null) {
                fullN.append(" ").append(secondLastName);
            }

            fullName = fullN.toString();
        }

        return fullName;
    }
}
