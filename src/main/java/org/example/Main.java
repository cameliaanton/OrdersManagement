package org.example;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Order;
import Model.Product;
import Presentation.View;
import Presentation.ViewClients;
/**

 The main class of the application.
 */
public class Main {
    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    /**

     The entry point of the application.
     @param args The command line arguments.
     */
    public static void main(String[] args) {
        View frame= new View();
    }
}