package fr.ensibs.orders.model;

public class Order {
    private int id;

    private String userName;

    private float price;

    private boolean paid;

    public Order() {
    }

    public Order(int id, String userName, float price, boolean paid) {
        this.id = id;
        this.userName = userName;
        this.price = price;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
