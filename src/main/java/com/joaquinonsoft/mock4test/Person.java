package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.dto.FamilyName;
import com.joaquinonsoft.mock4test.dto.Name;
import com.joaquinonsoft.mock4test.util.DateOfBirthGenerator;
import com.joaquinonsoft.mock4test.util.RndUtil;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Getter
public class Person extends Field{
    @Getter(AccessLevel.NONE)
    private volatile static List<FamilyName> familyNames;
    @Getter(AccessLevel.NONE)
    private volatile static List<Name> femaleNames;
    @Getter(AccessLevel.NONE)
    private volatile static List<Name> maleNames;

    private String bio;
    private String firstName;
    private String fullName;
    private String gender;
    private String jobArea;
    private String jobDescriptor;
    private String jobTitle;
    private String jobType;
    private String lastName;
    private String secondLastName;
    private String middleName;
    private final Sex sex;
    private final ZodiacSign zodiacSign;
    private final LocalDate birthdate;

    public Person() throws Mock4TestException {
        this(Locale.forLanguageTag("es-ES"));
    }

    public Person(Locale locale) throws Mock4TestException {
        super(locale);

        initNamesDictionary();

        if(familyNames != null){
            lastName = getFamilyName();

            if(locale.getLanguage().compareTo("es") == 0){
                secondLastName = getFamilyName();
            }
        }

        if(RndUtil.getInstance().nextFloat() <= 0.5f){
            sex = Sex.FEMALE;
            if (maleNames != null) {
                firstName = getMaleName();

                if(RndUtil.getInstance().nextFloat() <= 0.15f){
                    middleName = getMaleName();
                }
            }
        }
        else{
            sex = Sex.MALE;
            if (femaleNames != null) {
                firstName = getFemaleName();

                if(RndUtil.getInstance().nextFloat() <= 0.15f){
                    middleName = getFemaleName();
                }
            }
        }

        initFullName();

        birthdate = DateOfBirthGenerator.generateRandomDateOfBirth();
        zodiacSign = ZodiacSign.getZodiacSignFromDate(birthdate);
    }




    @SuppressWarnings("unchecked")
    private void initNamesDictionary() throws Mock4TestException {
        if(maleNames == null) {
            synchronized (this) {
                if(maleNames == null) {
                    try {
                        maleNames = (List<Name>) loadCSV("person/male-name", Name.class);
                        femaleNames = (List<Name>) loadCSV("person/female-name", Name.class);
                        familyNames = (List<FamilyName>) loadCSV("person/family-name", FamilyName.class);

                    } catch (FileNotFoundException e) {
                        throw new Mock4TestException(e);
                    }
                }
            }
        }
    }

    private void initFullName() {
        StringBuilder fullN = new StringBuilder();

        fullN.append(firstName);
        if(middleName != null){
            fullN.append(" ").append(middleName);
        }
        if(lastName != null){
            fullN.append(" ").append(lastName);
        }
        if(secondLastName != null){
            fullN.append(" ").append(secondLastName);
        }

        fullName = fullN.toString();
    }

    private static String getFamilyName() {
        return familyNames.get(RndUtil.getInstance().nextIntInRange(0, familyNames.size() - 1)).getFamilyName();
    }

    private String getFemaleName() {
        return femaleNames.get(RndUtil.getInstance().nextIntInRange(0, femaleNames.size() - 1)).getName();
    }

    private String getMaleName() {
        return maleNames.get(RndUtil.getInstance().nextIntInRange(0, maleNames.size() - 1)).getName();
    }


}
