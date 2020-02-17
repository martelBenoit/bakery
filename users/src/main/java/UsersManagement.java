import entity.User;

import java.util.List;

public interface UsersManagement {

    List<User> getUsers();

    boolean addUser(User user);

    boolean removeUser(int idUser);



}
