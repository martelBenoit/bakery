package fr.ensibs.service;

public interface PaymentsManagementService {

    void pay(int id, String token);

    void getBill(int id, String token);
}