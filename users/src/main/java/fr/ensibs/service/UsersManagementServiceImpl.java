package fr.ensibs.service;

import fr.ensibs.database.dao.UserDAO;
import fr.ensibs.database.entity.User;
import fr.ensibs.database.util.Role;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@WebService(endpointInterface = "fr.ensibs.service.UsersManagementService",serviceName = "UsersManagementService",portName = "UsersManagementPort")
public class UsersManagementServiceImpl implements UsersManagementService {

    UserDAO userDAO = new UserDAO();

    public User register(String login, String password, String roleString) throws Exception {

        Role role;

        if(roleString.equalsIgnoreCase("admin"))
            role = Role.admin;
        else if(roleString.equalsIgnoreCase("customer"))
            role = Role.customer;
        else
            throw new Exception("The role not exist, please enter a valid role (`admin` or `customer`");

        User user = new User(login,password,role);
        user = userDAO.addUser(user);
        return user;

    }

    public boolean unregister(int id, String token) {
        return false;
    }

    public List<User> getUsers(String token) throws Exception{
        User user = getUserFromToken(token);
        if(user != null && user.getRole() == Role.admin){
            try{
                return userDAO.getUsers();
            }catch (SQLException e){
               throw new Exception("Error when searching for users in the database");
            }
        }
        else
            throw new Exception("Your token is invalid for this command !");
    }

    public String auth(String login, String password) throws Exception {

        User user;
        try{
            user = userDAO.getUser(login,password);
            if(user != null){
                user.setToken("USR-"+UUID.randomUUID().toString());
                userDAO.updateUser(user);
                return user.getToken();
            }
            else
                throw new Exception("Invalid login or password");

        }catch(SQLException e){
            throw new Exception(e.getMessage());
        }

    }

    public boolean disconnect(String token) {
        boolean ret = false;
        User user = getUserFromToken(token);
        if(user != null){
            user.setToken("");
            try{
                ret = userDAO.updateUser(user);

            }catch(SQLException e){
                System.out.println("Unable to disconnect the user");
            }

        }
        return ret;
    }

    public User getUserFromToken(String token) {
        User user;
        try{
            user = userDAO.getUser(token);
            if(user != null){
                return user;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean userIsAdmin(String token) throws Exception{
        try{
            User user = userDAO.getUser(token);
            if(user == null)
                throw new Exception("The user doesn't exist !");
            else
                return user.getRole() == Role.admin;

        }catch (Exception e){
            throw new Exception("Error when searching in database : "+e.getMessage());
        }

    }
}
