package fr.ensibs.service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "fr.ensibs.service.PaymentsManagementService", serviceName = "PaymentsManagementService", portName = "PaymentsManagementPort")
public class PaymentsManagementServiceImpl implements PaymentsManagementService {

    private OrdersManagementService ordersManagementService;

    /**
     * Constructor
     */
    public PaymentsManagementServiceImpl() {
        this.ordersManagementService = new OrdersManagementService_Service().getOrdersManagementPort();
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
