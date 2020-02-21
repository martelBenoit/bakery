package fr.ensibs.service;

import javax.xml.ws.Endpoint;

/**
 * OrdersManagementPublisher class.
 * <p>
 * Entry point of the service.
 * <p>
 * Allow to publish the order management service.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 * @see OrdersManagementServiceImpl
 */
public class OrdersManagementPublisher {

    /**
     * The entry point of the service.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9993/ws/OrdersManagementService", new OrdersManagementServiceImpl());
    }
}
