package Presentation;

import BusinessLogic.ProductBLL;
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
import java.util.List;

import static com.mysql.cj.MysqlType.NULL;
/**
 * The ViewProducts class represents the graphical user interface for managing products.
 */
public class ViewProducts implements ActionListener {
    private JFrame frameProducts= new JFrame("Products");
    // private JFrame frame = new JFrame("Products");
    private JPanel panel = new JPanel();
    private JLabel labelTitle1 = new JLabel("Enter the necessary data for ");
    private JLabel labelTitle2 = new JLabel("the desired operation.");

    private JTextField textID = new JTextField("");
    private JLabel labelID= new JLabel("ID: ");
    private JTextField textName = new JTextField("");
    private JLabel labelName = new JLabel("Name: ");
    private JTextField textPrice= new JTextField("");
    private JLabel labelPrice= new JLabel("Price:");
    private JTextField textStock= new JTextField("");
    private JLabel labelStock= new JLabel("Stock:");
    private JButton btnBack= new JButton("Back");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnUpdate= new JButton("Update");
    private JButton btnInsert = new JButton("Insert");
    private JTable table;
    private JScrollPane scroll;
    private JLabel labelExplicatii= new JLabel("Note*");
    private JLabel labelExpDelete= new JLabel("Delete: ID");
    private JLabel labelExpInsert= new JLabel("Insert: Name,Adress,Age");
    private JLabel labelExpUpdate= new JLabel("Update:");
    /**
     * Constructs a ViewProducts instance and initializes the GUI components.
     */
    public ViewProducts()
    {
        frameProducts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameProducts.setVisible(true);
        frameProducts.setResizable(false);
        frameProducts.setBounds(400,10,595,500);

        labelTitle1.setBounds(10,0,200,50);
        labelTitle2.setBounds(25,10,200,50);

        labelID.setBounds(30,40,60,50);
        textID.setBounds(65,50,100,30);

        labelName.setBounds(20,80,60,50);
        textName.setBounds(65,90,100,30);

        labelPrice.setBounds(20,120,60,50);
        textPrice.setBounds(65,130,100,30);

        labelStock.setBounds(20,160,60,50);
        textStock.setBounds(65,170,100,30);

        btnInsert.setBounds(10,220,165,40);
        btnInsert.setBackground(new Color(208, 177, 250));
        btnUpdate.setBounds(10,270,165,40);
        btnUpdate.setBackground(new Color(208, 177, 250));
        btnDelete.setBounds(10,320,165,40);
        btnDelete.setBackground(new Color(208, 177, 250));
        btnBack.setBounds(10,370,165,40);
        btnBack.setBackground(new Color(208, 177, 250));

        panel.setBackground(new Color(194, 148, 255));

        ProductBLL productBLL= new ProductBLL();
        /*String[] columnNames={"ID","Name","Price","Stock"};
        Object[][] data=clientBLL.getDataProducts();
        table= new JTable(data,columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        */
        List<Product> products=productBLL.findAllProduct();
        table= Reflection.createTable(products);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(200,10,350,400);

        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        frameProducts.add(scroll);
        frameProducts.add(btnBack);
        frameProducts.add(textStock);
        frameProducts.add(labelStock);
        frameProducts.add(btnDelete);
        frameProducts.add(btnUpdate);
        frameProducts.add(btnInsert);
        frameProducts.add(labelTitle2);
        frameProducts.add(textID);
        frameProducts.add(labelID);
        frameProducts.add(textName);
        frameProducts.add(labelName);
        frameProducts.add(textPrice);
        frameProducts.add(labelPrice);
        frameProducts.add(labelTitle1);
        frameProducts.add(panel);

        btnInsert.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);
    }
    /**
     * Closes the frame of the ViewProducts instance.
     */
    public void closeFrameProducts()
    {
        frameProducts.setVisible(false);
    }
    /**
     * Opens the frame of the ViewProducts instance.
     */
    public void openFrameProducts()
    {
        frameProducts.setVisible(true);
    }
    /**
     * Retrieves the ID entered in the ID text field.
     *
     * @return The ID value as an integer.
     */
    public int getID() {
        String s= textID.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(btnInsert, "Invalid ID!");
            return 0;
        }
    }
    /**
     * Retrieves the name entered in the name text field.
     *
     * @return The name value as a string.
     */
    public String getName() {
        return textName.getText();
    }
    /**
     * Retrieves the price entered in the price text field.
     *
     * @return The price value as an integer.
     */
    public int getPrice() {
        String s= textPrice.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(btnInsert, "Invalid Price!");
            return 0;
        }
    }
    /**
     * Retrieves the stock entered in the stock text field.
     *
     * @return The stock value as an integer.
     */
    public int getStock() {
        String s= textStock.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(btnInsert, "Invalid Stock!");
            return 0;
        }
    }
    /**
     * Refreshes the table with the latest product data.
     */
    private void refreshTable() {
        ProductBLL productBLL = new ProductBLL();
       /* String[] columnNames = {"ID", "Name", "Price", "Stock"};
        Object[][] data = productBLL.getDataProducts();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        */
        table=Reflection.refreshTable(table,productBLL.findAllProduct());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);

    }
    /**
     * Performs the insert action for a new product.
     *
     * @param e The action event.
     */
    private void InsertAction(ActionEvent e)
    {
        ProductBLL productBLL= new ProductBLL();
        if(getPrice()!=0 && getStock()!=0)
        {
            Product product= new Product(getName(),getPrice(),getStock());
            productBLL.insertProduct(product);
            this.refreshTable();
        }

    }
    /**
     * Performs the delete action for a product.
     *
     * @param e The action event.
     */
    private void DeleteAction(ActionEvent e)
    {
        ProductBLL productBLL= new ProductBLL();
        int id=this.getID();
        if(id!=0) {
            Product p=productBLL.findProductById(getID());
            if(p!=null){
                productBLL.deleteProduct(id);
                this.refreshTable();
            }
            else JOptionPane.showMessageDialog(btnInsert, "Invalid ID!");

        }
    }
    /**
     * Performs the update action for a product.
     *
     * @param e The action event.
     */
    private void UpdateAction(ActionEvent e)
    {
        ProductBLL productBLL= new ProductBLL();
        if(getStock()!=0 && getPrice()!=0)
        {
            Product p=productBLL.findProductById(getID());
            if(p!=null){
                Product product = new Product(getID(),getName(),getPrice(),getStock());
                productBLL.updateProduct(product);
                this.refreshTable();
            }
            else JOptionPane.showMessageDialog(btnInsert, "Invalid ID!");

        }

    }
    /**
     * Performs the back action to return to the main menu.
     *
     * @param e The action event.
     */
    private void BackAction(ActionEvent e)
    {
        frameProducts.dispose();
      //  ViewClients c= new ViewClients();
        View view= new View();
        frameProducts.setVisible(false);

    }
    /**
     * Handles the action performed by the buttons.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnInsert)
        {
            this.InsertAction(e);
        }
        else if(e.getSource()==btnDelete)
        {
            this.DeleteAction(e);
        }
        else if(e.getSource()==btnUpdate)
        {
            this.UpdateAction(e);
        }
        else if(e.getSource()==btnBack)
        {
            this.BackAction(e);
        }
    }
}
