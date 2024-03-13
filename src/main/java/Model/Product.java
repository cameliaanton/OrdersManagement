package Model;

/**
 * Represents a Product entity.
 */
public class Product {
    private int id;
    private String nume;
    private int pret;
    private int stoc;

    /**
     * Default constructor.
     */
    public Product() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id    The ID of the product.
     * @param nume  The name of the product.
     * @param pret  The price of the product.
     * @param stoc  The stock quantity of the product.
     */
    public Product(int id, String nume, int pret, int stoc) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
    }

    /**
     * Constructor without ID field.
     *
     * @param nume  The name of the product.
     * @param pret  The price of the product.
     * @param stoc  The stock quantity of the product.
     */
    public Product(String nume, int pret, int stoc) {
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set for the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Sets the name of the product.
     *
     * @param nume The name to set for the product.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public int getPret() {
        return pret;
    }

    /**
     * Sets the price of the product.
     *
     * @param pret The price to set for the product.
     */
    public void setPret(int pret) {
        this.pret = pret;
    }

    /**
     * Retrieves the stock quantity of the product.
     *
     * @return The stock quantity of the product.
     */
    public int getStoc() {
        return stoc;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stoc The stock quantity to set for the product.
     */
    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    /**
     * Returns a string representation of the Product object.
     *
     * @return A string representation of the Product object.
     */
    public String toString() {
        return "Product: [id=" + id + ", name=" + nume + ", pret=" + pret + ", stoc=" + stoc + "]";
    }
}
