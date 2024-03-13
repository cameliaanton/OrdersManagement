package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main view of the management system.
 */
public class View implements ActionListener {
    private JFrame frame = new JFrame("Tema2");
    private JPanel panel = new JPanel();
    private JButton btnClients = new JButton("Clients");
    private JButton btnProducts = new JButton("Products");
    private JButton btnOrders = new JButton("Orders");
    private JLabel labelTitlu = new JLabel("Management System");
    private JLabel labelExplicatie = new JLabel("*NOTE: Select the table in which you want to perform an operation.");

    /**
     * Constructs a new instance of the View.
     */
    public View() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(50, 50, 420, 520);

        JPanel content = new JPanel();
        content.setBackground(new Color(250, 222, 65));

        labelTitlu.setBounds(60, 80, 300, 50);
        labelTitlu.setFont(new Font("Arial", Font.BOLD, 28));

        labelExplicatie.setBounds(20, 445, 400, 50);
        labelExplicatie.setFont(new Font("Arial", Font.TRUETYPE_FONT, 12));

        btnClients.setBounds(100, 160, 200, 60);
        btnClients.setBackground(new Color(250, 228, 180));

        btnProducts.setBounds(100, 260, 200, 60);
        btnProducts.setBackground(new Color(250, 228, 180));

        btnOrders.setBounds(100, 360, 200, 60);
        btnOrders.setBackground(new Color(250, 228, 180));

        frame.add(labelExplicatie);
        frame.add(labelTitlu);
        frame.add(btnOrders);
        frame.add(btnProducts);
        frame.add(btnClients);
        frame.add(content);

        btnClients.addActionListener(this);
        btnProducts.addActionListener(this);
        btnOrders.addActionListener(this);
    }

    /**
     * Handles the action when the "Clients" button is clicked.
     *
     * @param e The action event.
     */
    private void ClientsAction(ActionEvent e) {
        frame.dispose();
        ViewClients c = new ViewClients();
        c.openFrameClients();
        frame.setVisible(false);
    }

    /**
     * Handles the action when the "Products" button is clicked.
     *
     * @param e The action event.
     */
    private void ProductsAction(ActionEvent e) {
        frame.dispose();
        ViewProducts c = new ViewProducts();
        c.openFrameProducts();
        frame.setVisible(false);
    }

    /**
     * Handles the action when the "Orders" button is clicked.
     *
     * @param e The action event.
     */
    private void OrderAction(ActionEvent e) {
        frame.dispose();
        ViewOrders c = new ViewOrders();
        c.openFrameOrders();
        frame.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClients) {
            this.ClientsAction(e);
        } else if (e.getSource() == btnProducts) {
            this.ProductsAction(e);
        } else if (e.getSource() == btnOrders) {
            this.OrderAction(e);
        }
    }
}
