package fr.ensibs.service;

import javax.xml.ws.Endpoint;

public class OrdersManagementPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9002/ws/OrdersManagementService", new OrdersManagementServiceImpl());
    }
}
