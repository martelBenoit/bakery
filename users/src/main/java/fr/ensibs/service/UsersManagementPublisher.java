package fr.ensibs.service;

import javax.xml.ws.Endpoint;

/**
 * UsersManagementPublisher class.
 * <p>
 * Entry point of the service.
 * <p>
 * Allow to publish the user management service.
 *
 * @author Benoît Martel
 * @version 1.0
 * @see UsersManagementServiceImpl
 */
public class UsersManagementPublisher {

    /**
     * The entry point of the service.
     *
     * @param args none
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9991/ws/UsersManagementService", new UsersManagementServiceImpl());
    }
}
