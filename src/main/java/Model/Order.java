package Model;

/**
 * Represents an Order entity.
 */
public class Order {
    private int id;
    private String numeClient;
    private String numeProdus;
    private int cantitate;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id          The ID of the order.
     * @param numeClient  The name of the client associated with the order.
     * @param numeProdus  The name of the product associated with the order.
     * @param cantitate   The quantity of the product in the order.
     */
    public Order(int id, String numeClient, String numeProdus, int cantitate) {
        this.id = id;
        this.numeClient = numeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
    }

    /**
     * Constructor without ID field.
     *
     * @param numeClient  The name of the client associated with the order.
     * @param numeProdus  The name of the product associated with the order.
     * @param cantitate   The quantity of the product in the order.
     */
    public Order(String numeClient, String numeProdus, int cantitate) {
        this.numeClient = numeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
    }

    /**
     * Retrieves the ID of the order.
     *
     * @return The ID of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id The ID to set for the order.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client associated with the order.
     *
     * @return The name of the client.
     */
    public String getNumeClient() {
        return numeClient;
    }

    /**
     * Sets the name of the client associated with the order.
     *
     * @param numeClient The name to set for the client.
     */
    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    /**
     * Retrieves the name of the product associated with the order.
     *
     * @return The name of the product.
     */
    public String getNumeProdus() {
        return numeProdus;
    }

    /**
     * Sets the name of the product associated with the order.
     *
     * @param numeProdus The name to set for the product.
     */
    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return The quantity of the product.
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param cantitate The quantity to set for the product.
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     * Returns a string representation of the Order object.
     *
     * @return A string representation of the Order object.
     */
    public String toString() {
        return "Order: [id=" + id + ", numeClient=" + numeClient + ", numeProdus=" + numeProdus + ", cantitate=" + cantitate + "]";
    }
}
