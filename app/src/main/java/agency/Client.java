package agency;

public class Client {
    private final String surname;
    private final String name;
    private final int yearBirth;

    /**
     * Create a new client with the following parameters
     *
     * @param surname   the surname of the client
     * @param name      the name of the client
     * @param yearBirth the year of birth of the client
     */
    public Client(String surname, String name, int yearBirth) {
        this.surname = surname;
        this.name = name;
        this.yearBirth = yearBirth;
    }
}
