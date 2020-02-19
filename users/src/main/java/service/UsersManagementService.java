package service;

import entity.User;

import java.util.List;

public interface UsersManagementService {

    List<User> getUsers();

    boolean addUser(User user);

    boolean removeUser(int idUser);



}