package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import BusinessLogic.validators.ClientAgeValidator;
import BusinessLogic.validators.EmailValidator;
import Model.Client;
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

/**
 * The ViewClients class represents the graphical user interface for managing clients.
 */
public class ViewClients implements ActionListener {
    private JFrame frameClients= new JFrame("Clients");
   // private JFrame frame = new JFrame("Clients");
    private JPanel panel = new JPanel();
    private JLabel labelTitle1 = new JLabel("Enter the necessary data for ");
    private JLabel labelTitle2 = new JLabel("the desired operation.");

    private JTextField textID = new JTextField("");
    private JLabel labelID= new JLabel("ID: ");
    private JTextField textName = new JTextField("");
    private JLabel labelName = new JLabel("Name: ");

    private JTextField textEmail= new JTextField("");
    private JLabel labelEmail= new JLabel("Email:");
    private JTextField textAdress= new JTextField("");
    private JLabel labelAdress= new JLabel("Address:");
    private JTextField textAge= new JTextField("");
    private JLabel labelAge= new JLabel("Age:");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnBack = new JButton("Back");
    private JButton btnUpdate= new JButton("Update");
    private JButton btnInsert = new JButton("Insert");
    private JTable table;
    private JLabel labelExplicatii= new JLabel("Note*");
    private JLabel labelExpDelete= new JLabel("Delete: ID");
    private JLabel labelExpInsert= new JLabel("Insert: Name,Adress,Age");
    private JLabel labelExpUpdate= new JLabel("Update:");
    /**
     * Creates a new instance of the ViewClients class.
     */
    public ViewClients(){
        frameClients.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameClients.setVisible(true);
        frameClients.setResizable(false);
        frameClients.setBounds(400,10,900,500);

        labelTitle1.setBounds(10,0,200,50);
        labelTitle2.setBounds(25,10,200,50);

        labelID.setBounds(30,40,60,50);
        textID.setBounds(65,50,100,30);

        labelName.setBounds(20,80,60,50);
        textName.setBounds(65,90,100,30);

        labelAdress.setBounds(10,120,60,50);
        textAdress.setBounds(65,130,100,30);

        labelEmail.setBounds(20,160,60,50);
        textEmail.setBounds(65,170,100,30);

        labelAge.setBounds(25,200,60,50);
        textAge.setBounds(65,210,100,30);

        btnInsert.setBounds(10,260,165,40);
        btnInsert.setBackground(new Color(149, 252, 152));
        btnUpdate.setBounds(10,310,165,40);
        btnUpdate.setBackground(new Color(149, 252, 152));
        btnDelete.setBounds(10,360,165,40);
        btnDelete.setBackground(new Color(149, 252, 152));
        btnBack.setBounds(10,410,165,40);
        btnBack.setBackground(new Color(149, 252, 152));

        panel.setBackground(new Color(96, 240, 100));

        ClientBLL clientBLL= new ClientBLL();
      /*  String[] columnNames={"Id","Name","Address","Email","Age"};
        Object[][] data=clientBLL.getDataClients();
        table= new JTable(data,columnNames);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
*/
        List<Client> clients=clientBLL.findAllClient();
        table= Reflection.createTable(clients);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(200,10,660,400);

        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(160);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(220);
        columnModel.getColumn(4).setPreferredWidth(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        frameClients.add(scroll);
        frameClients.add(textEmail);
        frameClients.add(btnBack);
        frameClients.add(labelEmail);
        frameClients.add(btnDelete);
        frameClients.add(btnUpdate);
        frameClients.add(btnInsert);
        frameClients.add(labelTitle2);
        frameClients.add(textID);
        frameClients.add(labelID);
        frameClients.add(textName);
        frameClients.add(labelName);
        frameClients.add(textAdress);
        frameClients.add(labelAdress);
        frameClients.add(textAge);
        frameClients.add(labelAge);
        frameClients.add(labelTitle1);
        frameClients.add(panel);

        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);
    }
    /**
     * Retrieves the ID entered in the text field.
     *
     * @return The ID as an integer value, or 0 if the ID is invalid.
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
     * Retrieves the name entered in the text field.
     *
     * @return The name as a string.
     */
    public String getName() {
        return textName.getText();
    }
    /**
     * Retrieves the email entered in the text field.
     *
     * @return The email as a string.
     */
    public String getEmail() {
        return textEmail.getText();
    }
    /**
     * Retrieves the address entered in the text field.
     *
     * @return The address as a string.
     */
    public String geAdress() {
        return textAdress.getText();
    }
    /**
     * Retrieves the age entered in the text field.
     *
     * @return The age as an integer value, or 0 if the age is invalid.
     */
    public int getAge() {
        String s= textAge.getText();
        try {
            int a=Integer.parseInt(s);
            return a;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(btnInsert, "Invalid Age!");
            return 0;
        }
    }
    /**
     * Refreshes the table with the latest data from the database.
     */
    private void refreshTable() {
        ClientBLL clientBLL = new ClientBLL();
        /*String[] columnNames={"Id","Name","Address","Email","Age"};
        Object[][] data = clientBLL.getDataClients();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        */
        table= Reflection.refreshTable(table,clientBLL.findAllClient());
        TableColumnModel columnModel= table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(160);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(220);
        columnModel.getColumn(4).setPreferredWidth(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    /**
     * Performs the insert action when the Insert button is clicked.
     *
     * @param e The action event.
     */
    private void InsertAction(ActionEvent e)
    {
        ClientBLL clientBLL= new ClientBLL();
        ClientAgeValidator ageVal=new ClientAgeValidator();
        EmailValidator emailVal= new EmailValidator();
        int c=ageVal.validate(getAge());
        int cc=emailVal.validate(getEmail());
        if(c==0)
        {
            JOptionPane.showMessageDialog(btnInsert, "Invalid Age!");
        }
        else if(cc==0)
        {
            JOptionPane.showMessageDialog(btnInsert, "Invalid Email!");
        }
        else {
            Client client= new Client(getName(),geAdress(),getEmail(),getAge());
            clientBLL.insertClient(client);
            this.refreshTable();
        }

    }
    /**
     * Performs the delete action when the Delete button is clicked.
     *
     * @param e The action event.
     */
    private void DeleteAction(ActionEvent e)
    {
        ClientBLL clientBLL= new ClientBLL();
        int id=this.getID();

        clientBLL.deleteClient(id);
        this.refreshTable();
    }
    /**
     * Performs the update action when the Update button is clicked.
     *
     * @param e The action event.
     */
    private void UpdateAction(ActionEvent e)
    {
        ClientBLL clientBLL= new ClientBLL();
        Client client = new Client(getID(),getName(),geAdress(),getEmail(),getAge());
        clientBLL.updateClient(client);
        this.refreshTable();
    }
    /**
     * Performs the back action when the Back button is clicked.
     *
     * @param e The action event.
     */
    private void BackAction(ActionEvent e)
    {
        frameClients.dispose();
        //  ViewClients c= new ViewClients();
        View view= new View();
        frameClients.setVisible(false);

    }
    /**
     * Handles the action events for the buttons.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnInsert)
        {
            if (textAdress.getText().equals("") || textName.getText().equals("") || textEmail.getText().equals("")|| textAge.getText().equals("")) {
                JOptionPane.showMessageDialog(btnInsert, "Nu s-au completat toate campurile necesare");
            } else if (!textEmail.getText().contains("@") || !textEmail.getText().contains(".")) {
                JOptionPane.showMessageDialog(btnInsert, "Invalid E-Mail!");
            }
            else this.InsertAction(e);
        }
        else if(e.getSource()==btnDelete)
        {
            ClientBLL clientBLL= new ClientBLL();
            Client c=clientBLL.findClientById(getID());
           // System.out.println(c.toString());
            if(c!=null)
            {
                this.DeleteAction(e);
            }
            else JOptionPane.showMessageDialog(btnDelete,"Invalid Id");
        }
        else if(e.getSource()==btnUpdate)
        {
            ClientBLL clientBLL= new ClientBLL();
            Client c=clientBLL.findClientById(getID());
            //System.out.println(c.toString());
            if(c!=null)
            {
                this.UpdateAction(e);
            }
            else JOptionPane.showMessageDialog(btnDelete,"Invalid Id");
        }if (e.getSource()==btnBack)
        {
            this.BackAction(e);
        }
    }
    /**
     * Set Visible false the ViewClients frame.
     */
    public void closeFrameClients()
    {
        frameClients.setVisible(false);
    }
    /**
     * Set Visible true the ViewClients frame.
     */
    public void openFrameClients()
    {
        frameClients.setVisible(true);
    }
}

