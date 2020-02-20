package fr.ensibs.service;

import fr.ensibs.database.dao.OrderDAO;

import javax.jws.WebService;

@WebService(endpointInterface = "fr.ensibs.service.PaymentsManagementService", serviceName = "PaymentsManagementService", portName = "PaymentsManagementPort")
public class PaymentsManagementServiceImpl implements PaymentsManagementService {

    /**
     * the payment price
     */
    private int price = 0;

    /**
     * Constructor
     */
    public PaymentsManagementServiceImpl() {
        OrdersManagementServiceImpl ordersManagementService;
    }

    /**
     * Used when the user pays all his orders
     *
     * @param login the user login
     */
    public void pay(String login) {
        List<Order> orders = ordersManagementService.getOrders(login);
        for (Order order : orders) {
            OrderDAO dao = new OrderDAO();
            dao.setPaid(order.getId());
        }
        this.price = 0;
    }

    /**
     * Used when the user requests his bill
     *
     * @param login the user login
     */
    public void getBill(String login) {
        List<Order> orders = ordersManagementService.getOrders(login);
        for (Order order : orders) {
            if (!order.isPaid()) {
                this.price = this.price + order.getPrice();
            }
        }
        System.out.println("You need to pay " + this.price + "€");
    }
}
