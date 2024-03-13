package DataAccess;

import Model.Product;

import java.util.logging.Logger;

/**
 * Data Access Object for handling operations related to the Product entity in the database.
 */
public class ProductDAO extends AbstractDAO<Product>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
}
