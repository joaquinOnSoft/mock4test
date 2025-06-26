# Vehicle

Create fake vehicle information containing: brand, family, model type energy...

 - *String* `brandName`: Vehicle manufacturer name
 - *String* `familyName`: Vehicle's family name
 - *String* `modelName`: Vehicle's model name
 - *YearMonth* `manufacturedFrom`: Manufacturing start date
 - *YearMonth* `manufacturedTo`: Manufacturing end date
 - *String* `typeName`: Vehicle's type name
 - *String* `typeFullName`: Vehicle's type full name
 - *String* `energy`: Fuel type (petrol, diesel, liquefied gas...)

## Example of use

```java
    // import com.joaquinonsoft.mock4test.Person;

    //Instanciate a Person object using the default locale `es_ES`
    Person personES = new Person();

    System.out.println.(personES.getFirstName()); //Output example: José
    System.out.println.(personES.getLastName()); //Output example: Perez
    System.out.println.(personES.getSecondLastName()); //Output example: García
    System.out.println.(personES.getFullName()); //Output example: José Perez García
    System.out.println.(personES.getNationalIdentityCard()); //Output example: 111111111A
    System.out.println.(personES.getSex()); //Output example: Masculino
    System.out.println.(personES.getBirthdate()); //Output example: 2002-05-23
```

```java
    // import com.joaquinonsoft.mock4test.Person;

    //Instanciate a Person object using locale french/France `fr_FR`
    Person personFR = new Person(new Locale("fr", "FR"));

    System.out.println.(personFR.getFirstName()); //Output example: Pierre
    System.out.println.(personFR.getLastName()); //Output example: Doillon
    System.out.println.(personFR.getFullName()); //Output example: Pierre Doillon
    System.out.println.(personFR.getNationalIdentityCard()); //Output example: 111111111A
    System.out.println.(personFR.getSex()); //Output example: Masculino
    System.out.println.(personFR.getBirthdate()); //Output example: 2002-05-23
```