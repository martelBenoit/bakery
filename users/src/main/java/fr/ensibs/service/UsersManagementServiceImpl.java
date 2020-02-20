package fr.ensibs.service;

import fr.ensibs.dao.UserDAO;
import fr.ensibs.model.User;
import fr.ensibs.util.Role;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * UserManagementServiceImpl class. Implements UserManagementService interface.
 *
 * @author Benoît Martel
 * @version 1.0
 * @see UsersManagementService
 */
@WebService(endpointInterface = "fr.ensibs.service.UsersManagementService",serviceName = "UsersManagementService",portName = "UsersManagementPort")
public class UsersManagementServiceImpl implements UsersManagementService {

    /**
     * the userDAO instance
     */
    UserDAO userDAO = new UserDAO();

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unregister(int id, String token) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String auth(String login, String password) throws Exception {

        User user = userDAO.getUser(login,password);
        if(user == null)
            throw new Exception("Invalid login or password");

        user.setToken("USR-"+UUID.randomUUID().toString());
        userDAO.updateUser(user);
        return user.getToken();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(String token) throws Exception {

        User user = getUserFromToken(token);
        if(user == null)
            throw new Exception("Unable to retrieve the user in database");

        user.setToken("");
        return userDAO.updateUser(user);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserFromToken(String token) throws Exception {

        User user = userDAO.getUser(token);
        if(user == null)
            throw new Exception("The user with this token doesn't exist !");

        return user;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userIsAdmin(String token) throws Exception{
        try{
            User user = userDAO.getUser(token);
            if(user == null)
                throw new Exception("The user doesn't exist !");

            return user.getRole() == Role.admin;

        }catch (Exception e){
            throw new Exception("Error when searching in database : "+e.getMessage());
        }

    }
}
