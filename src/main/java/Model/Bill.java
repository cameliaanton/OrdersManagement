package Model;

/**
 * Represents a bill.
 *
 * @param nameClient    the name of the client
 * @param productName   the name of the product
 * @param price         the price of the product
 * @param address       the address of the client
 */
public record Bill(String nameClient, String productName, double price, String address) {

}
