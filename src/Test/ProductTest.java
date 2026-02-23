package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testUpdateStock() {
        // Added ID (1) and Price (2.50) to match the new constructor
        Product p = new Product(1, "Newspaper", 2.50, 10);
        p.updateStock(5);
        assertEquals(15, p.getStockQuantity(), "Stock should increase by 5");
    }
    
    @Test
    void testEditName() {
        // Added ID (2) and Price (5.00) to match the new constructor
        Product p = new Product(2, "Old Name", 5.00, 10);
        p.setName("New Name");
        assertEquals("New Name", p.getName());
    }

    // New Test: Verifying the price updates correctly (User Story 2)
    @Test
    void testEditPrice() {
        Product p = new Product(3, "Magazine", 4.99, 20);
        p.setPrice(5.99);
        
        // When testing doubles (decimals), JUnit requires a "delta" (0.001) 
        // to handle slight rounding differences in Java math.
        assertEquals(5.99, p.getPrice(), 0.001, "Price should update correctly");
    }
    
    @Test
    void testProductConstructorAndGetters() {
        // Create a new product with specific test data
        Product p = new Product(99, "Comic Book", 3.50, 25);
        
        // Verify the ID is stored and retrieved correctly
        assertEquals(99, p.getId(), "Product ID should match the constructor input");
        
        // Verify the initial stock is correct before any updates happen
        assertEquals(25, p.getStockQuantity(), "Initial stock should match the constructor input");
        
        // Verify the other fields just to be perfectly thorough
        assertEquals("Comic Book", p.getName(), "Name should match the constructor input");
        assertEquals(3.50, p.getPrice(), 0.001, "Price should match the constructor input");
    }
    
    @Test
    void testSettersForIdAndStock() {
        Product p = new Product(1, "Test", 1.00, 10);
        
        p.setId(500);
        assertEquals(500, p.getId());
        
        p.setStockQuantity(100);
        assertEquals(100, p.getStockQuantity());
    }
}

