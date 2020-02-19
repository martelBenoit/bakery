package fr.ensibs.service;



import fr.ensibs.dao.OrderDAO;
import fr.ensibs.models.Order;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {


    public void add(String userName, float price) {
        try {
            OrderDAO dao = new OrderDAO();
            dao.addOrder(new Order(0, userName, price));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the orders from a specified user
     *
     * @param userName The name of the user to get orders
     * @param userToken The token corresponding to the userId
     * @return The list of the user's orders
     */
    public List<Order> getOrders(String userName, String userToken) {
        List<Order> ret = new ArrayList<Order>();
        try {
            OrderDAO dao = new OrderDAO();
            ret = dao.getOrders(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void printMenu() {
        System.out.println("oui");
    }
}
