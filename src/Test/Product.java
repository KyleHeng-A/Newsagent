package Test;

public class Product {
	private int id;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int id, String name, double price, int initialStock) {
        this.id = id;
        this.name = name;
        this.price = price;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
    
}
