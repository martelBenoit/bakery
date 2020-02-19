package service;

import entity.User;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService(endpointInterface = "service.UsersManagementService")
public class UsersManagementServiceImpl implements UsersManagementService {


    public List<User> getUsers() {
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

    public boolean removeUser(int idUser) {
        return false;
    }
}
