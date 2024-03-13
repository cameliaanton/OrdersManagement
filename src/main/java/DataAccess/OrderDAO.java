package DataAccess;

import Model.Order;

import java.util.logging.Logger;
/**
 * Data Access Object for handling operations related to the Order entity in the database.
 */
public class OrderDAO extends AbstractDAO<Order>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
}
