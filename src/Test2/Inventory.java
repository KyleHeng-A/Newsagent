package Test2;

import java.util.ArrayList;

public class Inventory {
    // The exact same Data Structure from the UI
    private ArrayList<Product2> products = new ArrayList<>();

    // Method to add products for the setup
    public void add(Product2 p) {
        products.add(p);
    }

    // Method to get the whole list for the tests to loop through
    public ArrayList<Product2> getProducts() {
        return products;
    }

    // Method to remove a product (used by the tests)
    public void remove(Product2 p) {
        products.remove(p);
    }

    public int size() {
        return products.size();
    }
}
