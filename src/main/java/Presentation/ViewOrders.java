package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;
import org.example.Reflection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the view for managing orders.
 */
public class ViewOrders implements ActionListener {
    private JFrame frameOrders= new JFrame("Orders");
    // private JFrame frame = new JFrame("Orders");
    private JPanel panel = new JPanel();
    private JLabel labelTitle1 = new JLabel("Enter the necessary data for ");
    private JLabel labelTitle2 = new JLabel("the desired operation.");

    private JTextField textID = new JTextField("");
    private JLabel labelID= new JLabel("ID: ");
    private JTextField textNameClient = new JTextField("");
    private JLabel labelNameClient = new JLabel("Name Client: ");
    private JTextField textNameProduct= new JTextField("");
    private JLabel labelNameProduct= new JLabel("Name Product:");
    private JTextField textQuantity= new JTextField("");
    private JLabel labelQuantity= new JLabel("Quantity:");

    private JButton btnDelete = new JButton("BILL");
    private JButton btnUpdate= new JButton("Update");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnBack = new JButton("Back");
    private JTable table;
    private JLabel labelExplicatii= new JLabel("Note*");
    private JLabel labelExpDelete= new JLabel("Delete: ID");
    private JLabel labelExpInsert= new JLabel("Insert: Name,Adress,Age");
    private JLabel labelExpUpdate= new JLabel("Update:");
    private JComboBox<String> comboNameClient= new JComboBox<String>();
    private JComboBox<String> comboNameProduct= new JComboBox<String>();
    /**
     * Creates a new instance of the ViewOrders class.
     */
    public ViewOrders()
    {
        frameOrders.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOrders.setVisible(true);
        frameOrders.setResizable(false);
        frameOrders.setBounds(400,10,760,500);

        labelTitle1.setBounds(10,0,200,50);
        labelTitle2.setBounds(25,10,200,50);

        labelID.setBounds(45,40,60,50);
        textID.setBounds(100,50,150,30);
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();

        java.util.List<String> clientNames = clientBLL.getClientNames();
        for (String name : clientNames) {
            comboNameClient.addItem(name);
        }
        java.util.List<String> productNames = productBLL.getProductNames();
        for (String name : productNames) {
            comboNameProduct.addItem(name);
        }
        labelNameClient.setBounds(15,80,120,50);
       // textNameClient.setBounds(100,90,100,30);
        comboNameClient.setBounds(100,90,150,30);

        labelNameProduct.setBounds(10,120,120,50);
       // textNameProduct.setBounds(100,130,100,30);
        comboNameProduct.setBounds(100,130,150,30);

        labelQuantity.setBounds(30,160,60,50);
        textQuantity.setBounds(100,170,150,30);

        btnInsert.setBounds(10,220,250,40);
        btnInsert.setBackground(new Color(177, 234, 250));
        btnUpdate.setBounds(10,270,250,40);
        btnUpdate.setBackground(new Color(177, 234, 250));
        btnDelete.setBounds(10,320,250,40);
        btnDelete.setBackground(new Color(177, 234, 250));
        btnBack.setBounds(10,370,250,40);
        btnBack.setBackground(new Color(177, 234, 250));

        panel.setBackground(new Color(141, 228, 252));

        OrderBLL orderBLL= new OrderBLL();
       /* String[] columnNames={"ID","Name Client","Name Product","Quantity"};
        Object[][] data=orderBLL.getDataOrders();
        table= new JTable(data,columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        */
        List<Order> orders=orderBLL.findAllOrder();
        table= Reflection.createTable(orders);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(290,10,420,400);

        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(80);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        frameOrders.add(scroll);
        frameOrders.add(comboNameClient);
        frameOrders.add(comboNameProduct);
       // frameOrders.add(textNameClient);
        frameOrders.add(labelNameClient);
        frameOrders.add(btnDelete);
        frameOrders.add(btnUpdate);
        frameOrders.add(btnInsert);
        frameOrders.add(labelTitle2);
        frameOrders.add(textID);
        frameOrders.add(labelID);
        frameOrders.add(btnBack);
      //  frameOrders.add(textNameProduct);
        frameOrders.add(labelNameProduct);
        frameOrders.add(textQuantity);
        frameOrders.add(labelQuantity);
        frameOrders.add(labelTitle1);
        frameOrders.add(panel);

        btnBack.addActionListener(this);
        btnDelete.addActionListener(this);
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
    }
    /**
     * Closes the orders frame.
     */
    public void closeFrameOrders()
    {
        frameOrders.setVisible(false);
    }
    /**
     * Opens the orders frame.
     */
    public void openFrameOrders()
    {
        frameOrders.setVisible(true);
    }
    /**
     * Retrieves the ID entered in the text field.
     *
     * @return The ID as an integer, or 0 if the input is not a valid integer.
     */
    public int getID() {
        String s= textID.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    /**
     * Retrieves the selected name client from the combo box.
     *
     * @return The selected name client as a string.
     */
    public String getNameClient() {
        return (String) comboNameClient.getSelectedItem();
    }
    /**
     * Retrieves the selected name product from the combo box.
     *
     * @return The selected name product as a string.
     */
    public String getNameProduct() {
        return (String) comboNameProduct.getSelectedItem();
    }
    /**
     * Retrieves the quantity entered in the text field.
     *
     * @return The quantity as an integer, or 0 if the input is not a valid integer.
     */
    public int getQuantity() {
        String s= textQuantity.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(btnInsert, "Invalid ID!");
            return 0;
        }
    }
    /**
     * Refreshes the table with the latest data from the database.
     */
    private void refreshTable() {
        OrderBLL orderBLL = new OrderBLL();
       /* String[] columnNames={"ID","Name Client","Name Product","Quantity"};
        Object[][] data = orderBLL.getDataOrders();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        */
        table=Reflection.refreshTable(table,orderBLL.findAllOrder());
        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(80);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Inserts a new order into the database based on the input data.
     *
     * @param e The action event.
     */
    private void InsertAction(ActionEvent e)
    {
        OrderBLL orderBLL= new OrderBLL();
        ProductBLL productBLL= new ProductBLL();
        Order order= new Order(getNameClient(),getNameProduct(),getQuantity());
        if(productBLL.findProductByNume(order.getNumeProdus()).getStoc()>=order.getCantitate())
        {
            orderBLL.insertOrder(order);
            this.refreshTable();
        }
        else JOptionPane.showMessageDialog(btnInsert, "Invalida cantitate!");

    }
    /**
     * Prints a bill for the given order.
     *
     * @param order The order for which to print the bill.
     * @throws FileNotFoundException If the bill file cannot be created.
     */
    private void printBill(Order order) throws FileNotFoundException {
        ProductBLL productBLL= new ProductBLL();
        ClientBLL clientBLL= new ClientBLL();
        Client client=clientBLL.findClientByName(order.getNumeClient());
        Product product=productBLL.findProductByNume(order.getNumeProdus());
        long total=product.getPret()*order.getCantitate();
        PrintWriter printWriter = new PrintWriter("D:\\JavaInteligei\\tema3_maven" + "\\bill" + order.getId() + ".txt");
        Bill bill= new Bill(order.getNumeClient(),order.getNumeProdus(),total,client.getAddress());
        printWriter.write("Comanda numarul:"+order.getId()+"s-a efectuat cu succes!\n");
        printWriter.write("Date Client: \n Nume:"+order.getNumeClient()+" \n Email:"+client.getEmail()+"\n Age"+client.getAge()+"\n");
        printWriter.write("Ati comandat "+order.getCantitate()+" de "+order.getNumeProdus()+"\n");
        printWriter.write("Total plata: "+total+" ("+order.getCantitate()+"*"+product.getPret()+")\n");
        printWriter.write("Comanda se va livra la adresa:"+client.getAddress()+"\n");
        printWriter.close();
        JOptionPane.showMessageDialog(btnDelete, "Comanda a fost trimisa cu succes!");

    }
    /**
     * Deletes an order from the database based on the selected ID.
     *
     * @param e The action event.
     * @throws FileNotFoundException If the bill file cannot be created.
     */
    private void DeleteAction(ActionEvent e) throws FileNotFoundException {
        OrderBLL orderBLL= new OrderBLL();
        int id=this.getID();
        if(id!=0)
        {
            Order o=orderBLL.findOrderById(id);
            if(o!=null)
            {
                System.out.println("AM AJUNS AICI");
                printBill(o);
                orderBLL.deleteOrder(id);
                this.refreshTable();
            }
            else JOptionPane.showMessageDialog(btnDelete, "Invalid ID!");
        }
        else JOptionPane.showMessageDialog(btnDelete, "Invalid ID!");

    }
    /**
     * Updates an order in the database based on the selected ID and input data.
     *
     * @param e The action event.
     */
    private void UpdateAction(ActionEvent e)
    {
        OrderBLL orderBLL= new OrderBLL();
        if(getID()!=0)
        {
            Order o=orderBLL.findOrderById(getID());
            if(o!=null)
            {
                Order order= new Order(getID(),getNameClient(),getNameProduct(),getQuantity());
                orderBLL.updateOrder(order);
                this.refreshTable();
            }else JOptionPane.showMessageDialog(btnDelete, "Invalid ID!");
        }else JOptionPane.showMessageDialog(btnDelete, "Invalid ID!");
    }
    /**
     * Return to the View frame
     */
    private void BackAction(ActionEvent e)
    {
        frameOrders.dispose();
        //  ViewClients c= new ViewClients();
        View view= new View();
        frameOrders.setVisible(false);

    }
    /**
     * Handles the action performed by the buttons.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnDelete) {
            try {
                this.DeleteAction(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource()== btnInsert){
            this.InsertAction(e);
        }
        else if(e.getSource()==btnUpdate){
            this.UpdateAction(e);
        }else if(e.getSource()==btnBack){
            this.BackAction(e);
        }
    }
}
