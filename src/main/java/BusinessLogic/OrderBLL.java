package BusinessLogic;

import BusinessLogic.validators.Validator;
import DataAccess.OrderDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic class for managing orders.
 */
public class OrderBLL {
    private OrderDAO orderDAO;
    /**
     * Constructs a new instance of the OrderBLL class.
     * Initializes orderDAO.
     */
    public OrderBLL()
    {
        orderDAO= new OrderDAO();
    }
    /**
     * Finds an order by its ID.
     *
     * @param id the ID of the order
     * @return the found order
     * @throws NoSuchElementException if the order is not found
     */
    public Order findOrderById(int id) {
        Order st= (Order) this.orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return (Order) st;
    }
    /**
     * Inserts a new order.
     *
     * @param order the order to insert
     * @return the inserted order's ID
     */
    public int insertOrder(Order order) {
        ProductBLL p= new ProductBLL();
        Product pp=p.findProductByNume(order.getNumeProdus());
        int c=0;
        if(order.getCantitate()<=pp.getStoc())
        {
            c=this.orderDAO.insert(order);
            //c=this.orderDAO.insert(order);
            pp.setStoc(pp.getStoc()-order.getCantitate());
            p.updateProduct(pp);
        }
        if(c==1)
        {
            System.out.println("inserarea s-a realizat cu succes");
            afisareAllOrder();
        }
        else System.out.println("nu s-a putut insera");
        return (int) c;
    }
    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     */
    public void deleteOrder(int id){
        this.orderDAO.delete(id);
        afisareAllOrder();
    }
    /**
     * Updates an order.
     *
     * @param order the order to update
     */
    public void updateOrder(Order order)
    {
        ProductBLL p= new ProductBLL();
        Product pp=p.findProductByNume(order.getNumeProdus()); // gaseste produsul curent
        Order o=orderDAO.findById(order.getId()); // comanda inainte de update
        Product ppp=p.findProductByNume(o.getNumeProdus());// produsul vechi
        if(order.getCantitate()<pp.getStoc())
        {
            if(order.getNumeProdus().equals(o.getNumeProdus())) // daca comanda da update pe acelasi produs
                pp.setStoc(pp.getStoc()-order.getCantitate()+o.getCantitate());
            else { //daca comanda da update pe produse diferite
                ppp.setStoc(ppp.getStoc()+o.getCantitate()); // stoc + order veche Cantitate
                pp.setStoc(pp.getStoc()-order.getCantitate());//stoc - orderCantitate
                p.updateProduct(ppp);
            }
            p.updateProduct(pp);
            this.orderDAO.update(order);
        }

        afisareAllOrder();

    }
    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    public List<Order> findAllOrder()
    {
        return this.orderDAO.findAll();
    }
    /**
     * Displays all orders.
     */
    public void afisareAllOrder()
    {
        List<Order> list = new LinkedList<Order>();
        list=findAllOrder();
       // System.out.println();
        for (int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i).toString());
        }

    }
    /**
     * Retrieves the data of all orders.
     *
     * @return a 2D array of order data
     */
    public Object[][] getDataOrders() {
        List<Order> list = new LinkedList<Order>();
        list=findAllOrder();
        Object[][] data=null;
        data= new Object[list.size()][4];
        for(int i=0;i<list.size();i++)
        {
            Order c= list.get(i);
            data[i][0]=c.getId();
            data[i][1]=c.getNumeClient();
            data[i][2]=c.getNumeProdus();
            data[i][3]=c.getCantitate();
        }
        return data;
    }
}
