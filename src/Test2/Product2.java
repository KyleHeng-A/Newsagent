package Test2;

public class Product2 {
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructor used by DeleteProductTest
    public Product2(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter for ID (Crucial for the p.getId() == 1 check)
    public int getId() {
        return id;
    }

    // Getter for Name (Crucial for the p.getName().equals() check)
    public String getName() {
        return name;
    }

    // Additional getters required for full functionality
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
