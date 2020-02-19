package fr.ensibs.service;

import fr.ensibs.models.User;
import fr.ensibs.util.Role;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface UsersManagementService {

   public User register(String name, String password, Role role);

   public boolean unregister(int id, String token);

   public List<User> getUsers(String token);

   public String authentification(String name, String password);

   public boolean deconnection(int id, String token);




}