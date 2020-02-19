package service;


import fr.ensibs.entity.User;
import util.Role;

import java.util.List;

public interface UsersManagementService {

   public User register(String name, String password, Role role);

   public boolean unregister(User user);

   public List<User> getUsers();


}