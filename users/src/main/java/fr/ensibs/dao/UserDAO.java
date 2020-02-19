package fr.ensibs.dao;


import fr.ensibs.models.User;
import fr.ensibs.util.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(){

        this.connection = ConnectionSQLite.getConnection();
    }


    public boolean addUser(User user) throws SQLException {
        String query = "INSERT INTO User(name,password,role) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setInt(3,user.getRole().ordinal());
        int res = preparedStatement.executeUpdate();

        return res == 1;

    }

    public List<User> getUsers() throws SQLException {
        String query = "SELECT id, name, password, token, role FROM User";
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);

        List<User> users = new ArrayList<User>();
        while(res.next()){
            users.add(new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("password"),
                    res.getString("token"),
                    Role.of(res.getInt("role"))
                    )
            );
        }

        return users;

    }


}
