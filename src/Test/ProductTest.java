package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testUpdateStock() {
        Product p = new Product("Newspaper", 10);
        p.updateStock(5);
        assertEquals(15, p.getStockQuantity(), "Stock should increase by 5");
    }
    
    @Test
    void testEditName() {
        Product p = new Product("Old Name", 10);
        p.setName("New Name");
        assertEquals("New Name", p.getName());
    }
}

