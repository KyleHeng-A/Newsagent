package com.system;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<SystemProduct> inventory = new ArrayList<>();
    private int nextId = 100; // Starting ID number

    public SystemProduct addProduct(String name, double price, int stock) {
        // Validation for Test 2
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Error: Price and stock cannot be negative.");
        }
        
        // Generate unique ID for Test 1
        String uniqueId = "ITEM-" + (nextId++);
        SystemProduct newProduct = new SystemProduct(uniqueId, name, price, stock);
        inventory.add(newProduct);
        return newProduct;
    }

    public String getFormattedInventory() {
        StringBuilder output = new StringBuilder();
        
        for (SystemProduct p : inventory) {
            // Logic for Test 3
            String stockDisplay = (p.getQuantity() == 0) ? "Out of Stock" : String.valueOf(p.getQuantity());
            output.append(p.getId()).append(" | ")
                  .append(p.getName()).append(" | ")
                  .append(stockDisplay).append("\n");
        }
        return output.toString();
    }
}
