package fr.ensibs.service;


import fr.ensibs.database.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UsersManagementService {


   /**
    * This method allows you to register an user in the database.
    *
    * @param login the login for this user
    * @param password the password for this user
    * @param role the role for this user. (The role must be `admin` or `customer`)
    * @return the new User with the auto increment id register in the object
    * @throws java.lang.Exception throw an exception if the user login already exist
    */
   User register(@WebParam(name="login") String login, @WebParam(name="password") String password, @WebParam(name="role") String role) throws Exception;

   boolean unregister(@WebParam(name="id") int id, @WebParam(name="token") String token);

   List<User> getUsers(@WebParam(name="token") String token) throws Exception;

   String auth(@WebParam(name="name") String name, @WebParam(name="password") String password) throws Exception;

   boolean disconnect(@WebParam(name="token") String token);

   User getUserFromToken(@WebParam(name="token") String token);

   boolean userIsAdmin(@WebParam(name="token") String token) throws Exception;


   


}