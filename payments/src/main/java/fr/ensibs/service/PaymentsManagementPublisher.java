package fr.ensibs.service;

import javax.xml.ws.Endpoint;

public class PaymentsManagementPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9992/ws/PaymentsManagementService", new PaymentsManagementServiceImpl());
    }
}
