package UI;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product> inventory = new ArrayList<>();
    private int nextId = 1;

    // User Story 1: Add Product
    public Product addProduct(String name, double price, int stock) {
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Error: Price and stock cannot be negative.");
        }
        Product p = new Product(nextId++, name, price, stock);
        inventory.add(p);
        return p;
    }

    // Get the full list for the UI Table (User Story 2)
    public List<Product> getInventory() {
        return inventory;
    }

    // Find a product by ID (Helper method)
    public Product findProductById(int id) {
        for (Product p : inventory) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // Not found
    }

    // User Story 5: Remove Product
    public boolean removeProduct(int id) {
        Product target = findProductById(id);
        if (target != null) {
            inventory.remove(target);
            return true;
        }
        return false;
    }
}
