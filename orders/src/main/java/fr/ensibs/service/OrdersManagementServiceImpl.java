package fr.ensibs.service;


import fr.ensibs.database.dao.OrderDAO;
import fr.ensibs.database.entity.Order;
import fr.ensibs.database.entity.Product;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {

    protected UsersManagementService usersManagementPort;

    public OrdersManagementServiceImpl() {
        UsersManagementService_Service usersManagementService = new UsersManagementService_Service();
        usersManagementPort = usersManagementService.getUsersManagementPort();
    }

    public void addOrder(String token, List<String> products) {
        try {
            // check if the token is correct
            User user = usersManagementPort.getUserFromToken(token);
            if (user != null) {
                OrderDAO dao = new OrderDAO();
                float totalPrice = 0;
                for (String product : products) {
                    float price = getPriceOf(product);
                    // If the product exists
                    if(price != -1) {
                        totalPrice += price;
                    }
                }
                dao.addOrder(new Order(0, user.getLogin(), totalPrice, false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the orders from a specified user
     *
     * @param token The token of the user
     * @return The list of the user's orders
     */
    public List<Order> getOrders(String token) {
        List<Order> ret = new ArrayList<>();
        User user = usersManagementPort.getUserFromToken(token);
        if (user != null) {
            try {
                OrderDAO dao = new OrderDAO();
                ret = dao.getOrders(user.getLogin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Gets the orders from all users
     *
     * @param token The token of the admin
     * @return The list of all the orders
     */
    public List<Order> getAllOrders(String token) {

        List<Order> ret = new ArrayList<>();
        try {
            if (usersManagementPort.userIsAdmin(token)) {
                OrderDAO dao = new OrderDAO();
                ret = dao.getAllOrders();
            }
        } catch (SQLException | Exception_Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<Product> getMenu() {
        ArrayList<Product> menu = new ArrayList<>();
        menu.add(new Product("Pain au chocolat", 0.9f));
        menu.add(new Product("Croissant", 0.8f));
        menu.add(new Product("Pain aux raisins", 1f));
        return menu;
    }

    public void setPaid(int id) {
        OrderDAO dao = new OrderDAO();
        dao.setPaid(id);
    }

    private float getPriceOf(String name) {
        for(Product p : this.getMenu()) {
            if(p.getName().equals(name)) {
                return p.getPrice();
            }
        }
        return -1;
    }
}
