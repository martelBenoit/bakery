package fr.ensibs.service;


import fr.ensibs.database.dao.OrderDAO;
import fr.ensibs.database.entity.Order;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {


    public void addOrder(String userName, List<String> products) {
        try {
            OrderDAO dao = new OrderDAO();
            float totalPrice = 0;
            for(String product : products) {
                totalPrice += getMenu().get(product);
            }
            dao.addOrder(new Order(0, userName, totalPrice, false));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the orders from a specified user
     *
     * @param login The name of the user to get orders
     * @return The list of the user's orders
     */
    public List<Order> getOrders(String login) {
        List<Order> ret = new ArrayList<>();
        try {
            OrderDAO dao = new OrderDAO();
            ret = dao.getOrders(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Map<String, Float> getMenu() {
        Map<String, Float> menu = new HashMap<>();
        menu.put("Pain au chocolat", 0.9f);
        menu.put("Croissant", 0.8f);
        menu.put("Pain aux raisins", 1f);
        return menu;
    }

    public void setPaid(int id) {
        OrderDAO dao = new OrderDAO();
        dao.setPaid(id);
    }
}
