package fr.ensibs.orders.model;

/**
 * Order class.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 */
public class Order {

    /**
     * The id of the product
     */
    private int id;

    /**
     * The login of the user concerned by the order
     */
    private String userName;

    /**
     * The total price of the order
     */
    private float price;

    /**
     * The paid state of the order
     */
    private boolean paid;

    /**
     * Empty constructor
     */
    public Order() {
    }

    /**
     * Constructor of the Order class
     * @param userName The login of the user of the order
     * @param price The total price of the order
     * @param paid The state of the order (true if the order is paid)
     */
    public Order(String userName, float price, boolean paid) {
        this.userName = userName;
        this.price = price;
        this.paid = paid;
    }

    /**
     * Constructor of the Order class with id
     * @param id The id of the order
     * @param userName The login of the user of the order
     * @param price The total price of the order
     * @param paid The state of the order (true if the order is paid)
     */
    public Order(int id, String userName, float price, boolean paid) {
        this.id = id;
        this.userName = userName;
        this.price = price;
        this.paid = paid;
    }

    /**
     * Get the id of the order
     * @return The id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Set the order id
     * @param id The new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the login of the user of the order
     * @return The login of the user of the order
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the login of the order
     * @param userName The new login
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the total price of the order
     * @return The total price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the new price of the order
     * @param price The new price
     */
    public void setPrice(float price) {

        this.price = price;
    }

    /**
     * Get the paid state of the order
     * @return True if the order is paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Set the paid state of the order
     * @param paid True if the order is paid
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
