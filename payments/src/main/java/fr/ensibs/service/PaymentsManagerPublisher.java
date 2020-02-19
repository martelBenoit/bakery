package fr.ensibs.service;

import javax.xml.ws.Endpoint;

public class PaymentsManagerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9991/ws/paymentssmanagementservice", new PaymentsManagementServiceImpl());
    }
}
