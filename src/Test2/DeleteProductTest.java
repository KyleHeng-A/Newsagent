package Test2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductTest {

    private Inventory inventory;
    private Product2 product1;
    private Product2 product2;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        product1 = new Product2(1, "The Irish Times", 2.50, 50);
        product2 = new Product2(2, "Tech Monthly", 5.99, 15);
        
        inventory.add(product1);
        inventory.add(product2);
    }

    @Test
    @DisplayName("Verify all Product getters for coverage")
    public void testProductGetters() {
        // This ensures the price and quantity getters are called (Coverage)
        assertEquals(2.50, product1.getPrice());
        assertEquals(50, product1.getQuantity());
        assertEquals(1, product1.getId());
        assertEquals("The Irish Times", product1.getName());
    }

    @Test
    @DisplayName("User Story 5: Delete Product by ID")
    public void deleteProductByIdTest() {
    	Product2 targetProduct = null;
        for (Product2 p : inventory.getProducts()) {
            if (p.getId() == 1) {
                targetProduct = p;
                break;
            }
        }
        
        assertNotNull(targetProduct);
        inventory.remove(targetProduct);
        
        assertEquals(1, inventory.size());
        assertFalse(inventory.getProducts().contains(product1));
    }

    @Test
    @DisplayName("User Story 5: Delete Product by Name")
    public void deleteProductByNameTest() {
    	Product2 targetProduct = null;
        for (Product2 p : inventory.getProducts()) {
            if (p.getName().equals("Tech Monthly")) {
                targetProduct = p;
                break;
            }
        }
        
        assertNotNull(targetProduct);
        inventory.remove(targetProduct);
        
        assertEquals(1, inventory.size());
        assertFalse(inventory.getProducts().contains(product2));
    }

    @Test
    @DisplayName("Logic Check: Handle non-existent product")
    public void deleteNonExistentProductTest() {
        Product2 targetProduct = null;
        for (Product2 p : inventory.getProducts()) {
            if (p.getId() == 99) { 
                targetProduct = p;
                break;
            }
        }
        
        assertNull(targetProduct);
        assertEquals(2, inventory.size(), "Size should remain 2 if nothing is found to delete.");
    }
}

