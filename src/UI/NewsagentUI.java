package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NewsagentUI extends JFrame {
    
    // Connect the UI to the Backend Manager
    private InventoryManager manager = new InventoryManager();

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
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        // 4. Bottom Panel (Actions for User Stories 3, 4, 5)
        JPanel actionPanel = new JPanel();
        JButton updateStockBtn = new JButton("Update Stock");
        JButton editBtn = new JButton("Edit Details");
        JButton removeBtn = new JButton("Remove Product");
        
        actionPanel.add(updateStockBtn);
        actionPanel.add(editBtn);
        actionPanel.add(removeBtn);
        add(actionPanel, BorderLayout.SOUTH);

        // 5. Button Logic (Events)
        addButton.addActionListener(e -> handleAddProduct());
        updateStockBtn.addActionListener(e -> handleUpdateStock());
        editBtn.addActionListener(e -> handleEditDetails());
        removeBtn.addActionListener(e -> handleRemoveProduct());
    }

    // --- Action Handlers ---

    private void handleAddProduct() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            // Calls the Manager to do the logic
            manager.addProduct(name, price, stock);

            refreshTable();
            nameField.setText("");
            priceField.setText("");
            stockField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and stock.", "Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdateStock() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product from the table first.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Product p = manager.findProductById(id);

        String input = JOptionPane.showInputDialog(this, "Enter amount to add to stock for " + p.getName() + ":");
        if (input != null && !input.trim().isEmpty()) {
            try {
                int amount = Integer.parseInt(input);
                p.updateStock(amount); // Uses logic from Product Test
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number entered.");
            }
        }
    }

    private void handleEditDetails() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product from the table first.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Product p = manager.findProductById(id);

        // Request new Name and Price
        String newName = JOptionPane.showInputDialog(this, "Edit Name:", p.getName());
        if (newName != null && !newName.trim().isEmpty()) {
            p.setName(newName);
        }

        String priceInput = JOptionPane.showInputDialog(this, "Edit Price ($):", p.getPrice());
        if (priceInput != null) {
            try {
                double newPrice = Double.parseDouble(priceInput);
                if (newPrice >= 0) p.setPrice(newPrice);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price entered. Price unchanged.");
            }
        }
        refreshTable();
    }

    private void handleRemoveProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product from the table to remove.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);

        // Confirms deletion action as per User Story 5
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to permanently remove '" + name + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            manager.removeProduct(id);
            refreshTable();
        }
    }

    // --- Table UI Logic ---

    private void refreshTable() {
        tableModel.setRowCount(0); 
        List<Product> list = manager.getInventory();

        for (Product p : list) {
            // Displays "Out of Stock" if quantity is zero
            String stockDisplay = (p.getStockQuantity() == 0) ? "Out of Stock" : String.valueOf(p.getStockQuantity());
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
