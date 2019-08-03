import graphql.schema.DataFetcher;
import type.Country;
import type.Person;

import java.util.*;

public class Data {

    private static Country COUNTRY1;
    private static Country COUNTRY2;

    private static Person PERSON1;
    private static Person PERSON2;
    private static Person PERSON3;

    private static List<Person> PERSONS;
    private static List<Country> COUNTRIES;

    static {
        COUNTRY1 = new Country("UK", "Europe");
        COUNTRY2 = new Country("USA", "North America");

        PERSON1 = new Person("1", "Adam", "Smith", COUNTRY1);
        PERSON2 = new Person("2", "Bob", "Dylan", COUNTRY2);
        PERSON3 = new Person("3", "Camila", "Camilovsky", COUNTRY2);

        COUNTRIES = Arrays.asList(COUNTRY1, COUNTRY2);
        PERSONS = Arrays.asList(PERSON1, PERSON2, PERSON3);
    }

    public static DataFetcher getPersonByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String personId = dataFetchingEnvironment.getArgument("id");
            return PERSONS
                    .stream()
                    .filter(person -> person.getId().equals(personId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getPersonBySurnameDataFetcher() {
        return dataFetchingEnvironment -> {
            String personSurname = dataFetchingEnvironment.getArgument("surname");
            return PERSONS
                    .stream()
                    .filter(person -> person.getSurname().equals(personSurname))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getCountryDataFetcher() {
        return dataFetchingEnvironment -> {
            Person person = dataFetchingEnvironment.getSource();
            Country countryName = person.getCountry();
            return COUNTRIES
                    .stream()
                    .filter(country -> country.getName().equals(countryName.getName()))
                    .findFirst()
                    .orElse(null);
        };
    }


}
