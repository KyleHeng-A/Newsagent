package Test;

public class Product {
    private String name;
    private int stockQuantity;

    public Product(String name, int initialStock) {
        this.name = name;
        this.stockQuantity = initialStock;
    }

    // Logic for User Story: Update stock quantity
    public void updateStock(int amount) {
        this.stockQuantity += amount; 
    }

    // Logic for User Story: Edit product details
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
