package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewsagentUI extends JFrame {
    
    // The exact same Data Structure from the console version!
    private ArrayList<Product> inventory = new ArrayList<>();
    private int nextId = 1;

    // GUI Components
    private JTextField nameField, priceField, stockField;
    private DefaultTableModel tableModel;
    private JTable productTable;

    public NewsagentUI() {
        // 1. Setup the Main Window
        setTitle("Newsagent Inventory System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. Top Panel (Inputs for User Story 1)
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Price ($):"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Initial Stock:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        JButton addButton = new JButton("Add Product");
        inputPanel.add(addButton);
        
        add(inputPanel, BorderLayout.NORTH);

        // 3. Center Panel (Table for User Story 2)
        String[] columns = {"ID", "Name", "Price", "Stock"};
        tableModel = new DefaultTableModel(columns, 0);
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        // 4. Bottom Panel (Placeholders for remaining User Stories)
        JPanel actionPanel = new JPanel();
        JButton updateStockBtn = new JButton("Update Stock");
        JButton editBtn = new JButton("Edit Details");
        JButton removeBtn = new JButton("Remove Product");
        
        actionPanel.add(updateStockBtn);
        actionPanel.add(editBtn);
        actionPanel.add(removeBtn);
        add(actionPanel, BorderLayout.SOUTH);

        // 5. Button Logic (Events)
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Temporary popups for the buttons we haven't coded yet
        updateStockBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update Stock coming soon!"));
        editBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit Details coming soon!"));
        removeBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Remove Product coming soon!"));
    }

    // Logic for User Story 1
    private void addProduct() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            // Validation rule from your User Story
            if (price < 0 || stock < 0) {
                JOptionPane.showMessageDialog(this, "Error: Price and stock cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save to Data Structure
            Product p = new Product(nextId++, name, price, stock);
            inventory.add(p);

            // Update the UI Table and clear inputs
            refreshTable();
            nameField.setText("");
            priceField.setText("");
            stockField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and stock.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Logic for User Story 2
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear the old table data

        for (Product p : inventory) {
            // Rule: Show "Out of Stock" if 0
            String stockDisplay = (p.getStockQuantity() == 0) ? "Out of Stock" : String.valueOf(p.getStockQuantity());
            
            // Format price to 2 decimal places
            Object[] row = {p.getId(), p.getName(), String.format("$%.2f", p.getPrice()), stockDisplay};
            tableModel.addRow(row);
        }
    }

    // Launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NewsagentUI gui = new NewsagentUI();
            gui.setVisible(true);
        });
    }
}
