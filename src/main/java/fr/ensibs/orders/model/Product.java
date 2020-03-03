package fr.ensibs.orders.model;

/**
 * Product class.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 */
public class Product {

    /**
     * The name of the product
     */
    private String name;

    /**
     * The price of the product
     */
    private float price;

    /**
     * The id of the product
     */
    private int id;

    /**
     * Empty constructor
     */
    public Product() {

    }

    /**
     * Constructor of the Product class
     * @param id The if of the product
     * @param name The name of the product
     * @param price The price of the product
     */
    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Get the name of the product
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product
     * @param name The new name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of the product
     * @return The price of the product
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the product
     * @param price The new price of the product
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the id of the product
     * @return The id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the product
     * @param id The new id of the product
     */
    public void setId(int id) {
        this.id = id;
    }
}
