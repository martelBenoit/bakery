package fr.ensibs.service;



import fr.ensibs.entity.Order;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "fr.ensibs.service.OrdersManagementService", serviceName = "OrdersManagementService", portName = "OrdersManagementPort")
public class OrdersManagementServiceImpl implements OrdersManagementService {


    public Order add(String nom) {
        return null;
    }

    /**
     * Gets the orders from a specified user
     *
     * @param id The id of the user to get orders
     * @return The list of the user's orders
     */
    public List<Order> getOrders(int id) {
        return new ArrayList<Order>();
    }
}
