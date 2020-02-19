package fr.ensibs.entity;

public class Payment {

    /**
     *
     */
    private User user;

    /**
     *
     */
    private int price;

    /**
     * Empty constructor
     */
    public Payment() {

    }

    /**
     * Constructor
     *
     * @param user  the user who made the payment
     * @param price the payment amount
     */
    public Payment(User user, int price) {
        this.user = user;
        this.price = price;
    }

    //--------------------------------------------------
    // GETTERS
    //--------------------------------------------------

    /**
     * Give the user who made the payment
     *
     * @return the user who made the payment
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Give the payment amount
     *
     * @return the payment amount
     */
    public int getPrice() {
        return this.price;
    }

    //--------------------------------------------------
    // SETTERS
    //--------------------------------------------------

    /**
     * Set the user who made the payment
     *
     * @param user the user who made the payment
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Set the payment amount
     *
     * @param price the payment amount
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
