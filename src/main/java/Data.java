import graphql.schema.DataFetcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private static Map<String, String> PERSON1 = new HashMap<String, String>() {{
        put("id", "1");
        put("name", "Adam");
        put("surname", "Smith");
        put("country", "1");
    }};

    private static Map<String, String> PERSON2 = new HashMap<String, String>() {{
        put("id", "2");
        put("name", "Bob");
        put("surname", "Dylan");
        put("country", "2");
    }};

    private static Map<String, String> PERSON3 = new HashMap<String, String>() {{
        put("id", "3");
        put("name", "Camila");
        put("surname", "Camilovsky");
        put("country", "2");
    }};

    private static Map<String, String> COUNTRY1 = new HashMap<String, String>() {{
        put("id", "1");
        put("name", "UK");
        put("continent", "Europe");
    }};

    private static Map<String, String> COUNTRY2 = new HashMap<String, String>() {{
        put("id", "2");
        put("name", "USA");
        put("continent", "North America");
    }};

    private static List<Map<String, String>> PERSONS = Arrays.asList(PERSON1, PERSON2, PERSON3 );
    private static List<Map<String, String>> COUNTRIES = Arrays.asList(COUNTRY1, COUNTRY2 );

    public static DataFetcher getPersonByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String personId = dataFetchingEnvironment.getArgument("id");
            return PERSONS
                    .stream()
                    .filter(person -> person.get("id").equals(personId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getPersonByNameDataFetcher() {
        return dataFetchingEnvironment -> {
            String personName = dataFetchingEnvironment.getArgument("name");
            return PERSONS
                    .stream()
                    .filter(person -> person.get("name").equals(personName))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getCountryDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> person = dataFetchingEnvironment.getSource();
            String countryId = person.get("country");
            return COUNTRIES
                    .stream()
                    .filter(country -> country.get("id").equals(countryId))
                    .findFirst()
                    .orElse(null);
        };
    }

}
