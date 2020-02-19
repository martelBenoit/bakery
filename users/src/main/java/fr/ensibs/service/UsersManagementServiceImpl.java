package fr.ensibs.service;



import fr.ensibs.models.User;
import fr.ensibs.util.Role;

import javax.jws.WebService;
import java.util.List;


@WebService(endpointInterface = "fr.ensibs.service.UsersManagementService",serviceName = "UsersManagementService",portName = "UsersManagementPort")
public class UsersManagementServiceImpl implements UsersManagementService {


    public User register(String name, String password, Role role) {
        return null;
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
