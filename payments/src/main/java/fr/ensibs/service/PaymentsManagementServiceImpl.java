package fr.ensibs.service;

import javax.jws.WebService;
import java.util.List;

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

    }

    /**
     * Used when the user pays all his orders
     *
     * @param id    the user id
     * @param token the user token
     */
    public void pay(int id, String token) {
        List<Order> orders = getOrders(id, token);
        for (Order order : orders) {
            order.setPaid(true);
        }
        this.price = 0;
    }

    /**
     * Used when the user requests his bill
     *
     * @param id    the user id
     * @param token the user token
     */
    public void getBill(int id, String token) {
        List<Order> orders = getOrders(id, token);
        for (Order order : orders) {
            if (!order.isPaid()) {
                this.price = this.price + order.getPrice();
            }
        }
        System.out.println("You need to pay " + this.price + "€");
    }
}
