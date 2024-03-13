package Model;
/**
 * Represents a Client entity.
 */
public class Client {
    private int id;
    private String nume;
    private String address;
    private String email;
    private int age;

    /**
     * Default constructor.
     */
    public Client() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id      The ID of the client.
     * @param nume    The name of the client.
     * @param address The address of the client.
     * @param email   The email of the client.
     * @param age     The age of the client.
     */
    public Client(int id, String nume, String address, String email, int age) {
        super();
        this.id = id;
        this.nume = nume;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructor without ID field.
     *
     * @param nume    The name of the client.
     * @param address The address of the client.
     * @param email   The email of the client.
     * @param age     The age of the client.
     */
    public Client(String nume, String address, String email, int age) {
        super();
        this.nume = nume;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return The ID of the client.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id The ID to set for the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The name of the client.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Sets the name of the client.
     *
     * @param nume The name to set for the client.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Retrieves the address of the client.
     *
     * @return The address of the client.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     *
     * @param address The address to set for the client.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the email of the client.
     *
     * @return The email of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email The email to set for the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the age of the client.
     *
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     *
     * @param age The age to set for the client.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the Client object.
     *
     * @return A string representation of the Client object.
     */
    public String toString() {
        return "Client: [id=" + id + ", nume=" + nume + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }

}
