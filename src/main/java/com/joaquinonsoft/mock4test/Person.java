package com.joaquinonsoft.mock4test;

import com.joaquinonsoft.mock4test.dto.FamilyName;
import com.joaquinonsoft.mock4test.dto.Name;
import com.joaquinonsoft.mock4test.util.RndUtil;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.FileNotFoundException;
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
    private String middleName;
    private final Sex sex;
    private String zodiacSign;

    public Person() throws Mock4DataException{
        this(Locale.forLanguageTag("es-ES"));
    }

    public Person(Locale locale) throws Mock4DataException{
        super(locale);

        initNames();

        if(familyNames != null){
            lastName = familyNames.get(RndUtil.getInstance().nextInRange(0, familyNames.size() - 1)).getFamilyName();
        }

        if(RndUtil.getInstance().next() <= 0.5f){
            sex = Sex.FEMALE;
            if (maleNames != null) {
                firstName = maleNames.get(RndUtil.getInstance().nextInRange(0, maleNames.size() - 1)).getName();
            }
        }
        else{
            sex = Sex.MALE;
            if (femaleNames != null) {
                firstName = femaleNames.get(RndUtil.getInstance().nextInRange(0, femaleNames.size() - 1)).getName();
            }
        }
    }


    private void initNames() throws Mock4DataException {
        if(maleNames == null) {
            synchronized (this) {
                if(maleNames == null) {
                    try {
                        maleNames = (List<Name>) loadCSV("person/male-name", Name.class);
                        femaleNames = (List<Name>) loadCSV("person/female-name", Name.class);
                        familyNames = (List<FamilyName>) loadCSV("person/family-name", FamilyName.class);

                    } catch (FileNotFoundException e) {
                        throw new Mock4DataException(e);
                    }
                }
            }
        }
    }
}
