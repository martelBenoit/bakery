package fr.ensibs.service;


import fr.ensibs.database.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UsersManagementService {


   User register(@WebParam(name="login") String login, @WebParam(name="password") String password, @WebParam(name="role") String role) throws Exception;

   boolean unregister(int id, String token);

   List<User> getUsers(String token) throws Exception;

   String auth(String name, String password) throws Exception;

   boolean disconnect(String token);

   User getUserFromToken(String token);

   boolean userIsAdmin(String token) throws Exception;


   


}