package fr.ensibs.service;

import javax.xml.ws.Endpoint;


/**
 * PaymentsManagementPublisher class.
 * <p>
 * Entry point of the service.
 * <p>
 * Allow to publish the payments management service.
 *
 * @author Loïc Travaillé
 * @version 1.0
 * @see PaymentsManagementServiceImpl
 */
public class PaymentsManagementPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9992/ws/PaymentsManagementService", new PaymentsManagementServiceImpl());
    }
}
