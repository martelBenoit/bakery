package fr.ensibs.payments.service;

import fr.ensibs.orders.model.Order;
import fr.ensibs.orders.service.OrdersManagementServiceImpl;
import fr.ensibs.users.service.UsersManagementService;

import javax.jws.WebService;
import java.util.List;

/**
 * PaymentsManagementServiceImpl class implements PaymentsManagementService interface.
 *
 * @author Loïc Travaillé
 * @version 1.0
 * @see PaymentsManagementService
 */
@WebService(endpointInterface = "fr.ensibs.payments.service.PaymentsManagementService", serviceName = "PaymentsManagementService", portName = "PaymentsManagementPort")
public class PaymentsManagementServiceImpl implements PaymentsManagementService {

    private OrdersManagementServiceImpl ordersManagementService = OrdersManagementServiceImpl.getInstance();

    /**
     * Constructor
     */
    public PaymentsManagementServiceImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pay(String token) {
        List<Order> orders = ordersManagementService.getOrders(token);
        for (Order order : orders) {
            if (!order.isPaid()) {
                ordersManagementService.setPaid(token, order.getId());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getBill(String token) {
        float price = 0;
        List<Order> orders = ordersManagementService.getOrders(token);
        for (Order order : orders) {
            if (!order.isPaid()) {
                price = price + order.getPrice();
            }
        }
        return price;
    }
}
