package fr.ensibs.service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "fr.ensibs.service.PaymentsManagementService", serviceName = "PaymentsManagementService", portName = "PaymentsManagementPort")
public class PaymentsManagementServiceImpl implements PaymentsManagementService {

    /**
     * the payment price
     */
    private float price = 0;

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
            ordersManagementService.setPaid(order.getId());
        }
        this.price = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getBill(String token) {
        List<Order> orders = ordersManagementService.getOrders(token);
        for (Order order : orders) {
            if (!order.isPaid()) {
                this.price = this.price + order.getPrice();
            }
        }
        System.out.println("You need to pay " + this.price + "€");
    }
}
