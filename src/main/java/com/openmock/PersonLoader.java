package com.openmock;

import com.openmock.dto.FamilyNameDTO;
import com.openmock.dto.JobDTO;
import com.openmock.dto.NameDTO;
import com.openmock.identitycard.IIdentityCard;
import com.openmock.identitycard.IdentityCardFactory;
import com.openmock.util.DateOfBirthGenerator;
import com.openmock.util.RndUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Log4j2
@Getter
public class PersonLoader extends Field{
    private volatile static List<FamilyNameDTO> familyNames;
    private volatile static List<NameDTO> femaleNames;
    private volatile static List<NameDTO> maleNames;
    private volatile static List<JobDTO> jobs;

    public PersonLoader() throws OpenMockException {
        this(Locale.forLanguageTag(LANG_TAG_ES_ES));
    }

    public PersonLoader(Locale locale) throws OpenMockException {
        super(locale);
        initDictionaries();
    }

    @SuppressWarnings("unchecked")
    private void initDictionaries() throws OpenMockException {
        if(maleNames == null) {
            synchronized (this) {
                if(maleNames == null) {
                    try {
                        maleNames = (List<NameDTO>) loadCSV("person/male-name", NameDTO.class);
                        femaleNames = (List<NameDTO>) loadCSV("person/female-name", NameDTO.class);
                        familyNames = (List<FamilyNameDTO>) loadCSV("person/family-name", FamilyNameDTO.class);

                        jobs = (List<JobDTO>) loadCSV("person/job", JobDTO.class);
                    } catch (FileNotFoundException e) {
                        throw new OpenMockException(e);
                    }
                }
            }
        }
    }

    public Person getPerson(){
        Person person = new Person();


        if(familyNames != null){
            person.setLastName(getFamilyName());

            if(locale.getLanguage().compareTo(LANG_ES) == 0){
                person.setSecondLastName(getFamilyName());
            }
        }

        Sex sex;
        if(RndUtil.getInstance().nextFloat() <= 0.5f){
            sex = Sex.MALE;
            if (maleNames != null) {
                person.setFirstName(getMaleName());

                if(RndUtil.getInstance().nextFloat() <= 0.15f){
                    person.setMiddleName(getMaleName());
                }
            }
        }
        else{
            sex = Sex.FEMALE;
            if (femaleNames != null) {
                person.setFirstName(getFemaleName());

                if(RndUtil.getInstance().nextFloat() <= 0.15f){
                    person.setMiddleName(getFemaleName());
                }
            }
        }
        person.setSex(sex);

        //Init national identity card
        try {
            IIdentityCard idCard = IdentityCardFactory.getIdentityCard(locale);
            person.setNationalIdentityCard(idCard.generateId());
        } catch (IllegalArgumentException e) {
            log.error("Identity card generation: ");
        }


        //Init jobs
        if(jobs != null) {
            JobDTO job = jobs.get(RndUtil.getInstance().nextIntInRange(0, jobs.size() - 1));

            person.setJobArea(job.getJobArea());
            person.setJobDescriptor(job.getJobDescriptor());
            person.setJobTitle(job.getJobTitle());
            person.setJobType(job.getJobType());
        }

        LocalDate birthdate = DateOfBirthGenerator.generate();
        person.setBirthdate(birthdate);
        person.setZodiacSign(ZodiacSign.getZodiacSignFromDate(birthdate));

        return person;
    }

    private String getFamilyName() {
        return familyNames == null? "" : familyNames.get(RndUtil.getInstance().nextIntInRange(0, familyNames.size() - 1)).getFamilyName();
    }

    private String getFemaleName() {
        return femaleNames == null? "" : femaleNames.get(RndUtil.getInstance().nextIntInRange(0, femaleNames.size() - 1)).getName();
    }

    private String getMaleName() {
        return maleNames == null? "" : maleNames.get(RndUtil.getInstance().nextIntInRange(0, maleNames.size() - 1)).getName();
    }
}
