package fr.ensibs.service;



import fr.ensibs.dao.UserDAO;
import fr.ensibs.models.User;
import fr.ensibs.util.Role;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;


@WebService(endpointInterface = "fr.ensibs.service.UsersManagementService",serviceName = "UsersManagementService",portName = "UsersManagementPort")
public class UsersManagementServiceImpl implements UsersManagementService {

    UserDAO userDAO = new UserDAO();

    public User register(String name, String password, Role role) {
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

    public String authentification(String name, String password) {
        return null;
    }

    public boolean deconnection(int id, String token) {
        return false;
    }
}
