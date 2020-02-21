package fr.ensibs.service;


import fr.ensibs.model.User;

import javax.jws.WebParam;
import javax.jws.WebService;

import java.util.List;

/**
 * Interface UserManagementService.
 *
 * @author Benoît Martel
 * @version 1.0
 */
@WebService(name = "UsersManagementService")
public interface UsersManagementService {


    /**
     * This method allows you to register an user in the database.
     *
     * @param login    the login for this user
     * @param password the password for this user
     * @param role     the role for this user. (The role must be `admin` or `customer`)
     * @return the new User with the auto increment id register in the object
     * @throws java.lang.Exception throw an exception if the user login already exist
     */
    User register(@WebParam(name = "login") String login, @WebParam(name = "password") String password, @WebParam(name = "role") String role) throws Exception;

    /**
     * This method allows you to unregister an user in the database.
     *
     * @param id    the id of the user to unregister
     * @param token the token of an admin user
     * @return true if the unregister is realized
     */
    boolean unregister(@WebParam(name = "id") int id, @WebParam(name = "token") String token);

    /**
     * This method allows you to get all users in the database.
     *
     * @param token the token of an admin user
     * @return the list of users
     * @throws java.lang.Exception throw an exception if the token is not valid
     */
    List<User> getUsers(@WebParam(name = "token") String token) throws Exception;

    /**
     * This method allows you to auth an user and return the token.
     *
     * @param login    the login of the user to auth
     * @param password the password of the user to auth
     * @return the token
     * @throws java.lang.Exception throw an exception if the login or/and password is invalid
     */
    String auth(@WebParam(name = "login") String login, @WebParam(name = "password") String password) throws Exception;

    /**
     * This method allows you to disconnect an user.
     *
     * @param token the token of the user to disconnect
     * @return true if the disconnection is realized, else false
     * @throws java.lang.Exception throw an exception if the token doesn't exist
     */
    boolean disconnect(@WebParam(name = "token") String token) throws Exception;

    /**
     * This method allows you to get an user from a token.
     *
     * @param token the token of the user to retrieve.
     * @return the user
     * @throws java.lang.Exception throw an exception if the token doesn't exist
     */
    User getUserFromToken(@WebParam(name = "token") String token) throws Exception;

    /**
     * This method allows you to know if an user is admin.
     *
     * @param token the token of the user
     * @return true if the user is an admin
     * @throws java.lang.Exception throw an exception if the token doesn't exist
     */
    boolean userIsAdmin(@WebParam(name = "token") String token) throws Exception;


}