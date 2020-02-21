package fr.ensibs.payments.service;


import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Interface PaymentsManagementService.
 *
 * @author Loïc Travaillé
 * @version 1.0
 */
@WebService(name = "PaymentsManagementService")
public interface PaymentsManagementService {

    /**
     * This method is used when a user pays his bill
     *
     * @param token the user token
     */
    void pay(@WebParam(name = "token") String token);

    /**
     * This method give the total bill of a user
     *
     * @param token the user token
     * @return the total user bill
     */
    float getBill(@WebParam(name = "token") String token);
}