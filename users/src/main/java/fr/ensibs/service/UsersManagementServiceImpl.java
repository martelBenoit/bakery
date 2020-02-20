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

    public User register(String name, String password, String roleString) {

        Role role;

        if(roleString.equalsIgnoreCase("admin"))
            role = Role.admin;
        else if(roleString.equalsIgnoreCase("customer"))
            role = Role.customer;
        else
            return null;


        User user = new User(name,password,role);
        try{
            user = userDAO.addUser(user);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public boolean unregister(int id, String token) {
        return false;
    }

    public List<User> getUsers(String token) {
        return null;
    }

    public String authentification(String login, String password) {

        User user;
        try{
            user = userDAO.getUser(login,password);
            if(user != null){
                user.setToken("USR-"+UUID.randomUUID().toString());
                userDAO.updateUser(user);
                return user.getToken();
            }

        }catch(SQLException e){
            e.printStackTrace();
            return "";
        }
        return "";

    }

    public boolean deconnection(String token) {
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
}
