package fr.ensibs.service;


import fr.ensibs.database.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UsersManagementService {


   User register(@WebParam(name="login") String login, @WebParam(name="password") String password, @WebParam(name="role") String role);

   boolean unregister(int id, String token);

   List<User> getUsers(String token);

   String authentification(String name, String password);

   boolean deconnection(String token);

   User getUserFromToken(String token);


   


}