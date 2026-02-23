package com.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private InventoryManager manager;

    @BeforeEach
    void setUp() {
        manager = new InventoryManager();
    }

    @Test
    @DisplayName("Test 1: Successfully add product and check all getters")
    void testAddProductSuccess() {
        SystemProduct p = manager.addProduct("Daily News", 2.5, 50);
        
        assertNotNull(p.getId(), "ID should be generated");
        assertEquals("Daily News", p.getName(), "Name should match");
        
        // ADDED: Explicitly testing getPrice() and getQuantity()
        assertEquals(2.5, p.getPrice(), 0.001, "Price should match");
        assertEquals(50, p.getQuantity(), "Quantity should match");
    }

    @Test
    @DisplayName("Test 2a: Validation throws error for negative price")
    void testValidationNegativePrice() {
        // Tests the 'price < 0' side of the IF statement
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addProduct("Bad Price Mag", -1.0, 10);
        });
        assertEquals("Error: Price and stock cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Test 2b: Validation throws error for negative stock")
    void testValidationNegativeStock() {
        // ADDED: Tests the 'stock < 0' side of the IF statement
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addProduct("Bad Stock Mag", 5.0, -5);
        });
        assertEquals("Error: Price and stock cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Test 3a: Display 'Out of Stock' when quantity is 0")
    void testOutOfStockDisplay() {
        // Tests the true condition of the ternary operator
        manager.addProduct("Sold Out Mag", 10.0, 0);
        String output = manager.getFormattedInventory();
        
        assertTrue(output.contains("Out of Stock"), "Output should contain 'Out of Stock'");
    }

    @Test
    @DisplayName("Test 3b: Display actual quantity when stock is greater than 0")
    void testInStockDisplay() {
        // ADDED: Tests the false condition of the ternary operator (String.valueOf)
        manager.addProduct("In Stock Mag", 10.0, 42);
        String output = manager.getFormattedInventory();
        
        assertFalse(output.contains("Out of Stock"), "Output should NOT say 'Out of Stock'");
        assertTrue(output.contains("42"), "Output should display the actual stock quantity of 42");
    }
}