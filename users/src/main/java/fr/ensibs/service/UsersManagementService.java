package fr.ensibs.service;

import fr.ensibs.models.User;


import javax.jws.WebService;
import java.util.List;

@WebService
public interface UsersManagementService {


   User register(String name, String password, String role);

   boolean unregister(int id, String token);

   List<User> getUsers(String token);

   String authentification(String name, String password);

   boolean deconnection(String token);

   User getUserFromToken(String token);




}