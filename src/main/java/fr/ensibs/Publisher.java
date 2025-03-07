package fr.ensibs;

import fr.ensibs.orders.service.OrdersManagementServiceImpl;
import fr.ensibs.payments.service.PaymentsManagementServiceImpl;
import fr.ensibs.users.service.UsersManagementServiceImpl;

import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/UsersManagementService", new UsersManagementServiceImpl());
        Endpoint.publish("http://localhost:9999/ws/OrdersManagementService", new OrdersManagementServiceImpl());
        Endpoint.publish("http://localhost:9999/ws/PaymentsManagementService", new PaymentsManagementServiceImpl());
    }
}
