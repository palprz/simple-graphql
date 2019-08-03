package type;

public class Person {

    private String id;
    private String firstname;
    private String surname;
    private Country country;

    public Person(String id, String firstname, String surname, Country country) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
