package fr.ensibs.database.dao;


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


    public User addUser(User user) throws SQLException {
        String query = "INSERT INTO User(login,password,role) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setInt(3,user.getRole().ordinal());
        int res = preparedStatement.executeUpdate();

        if(res == 1){
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        }

        return user;

    }

    public User getUser(String login, String password) throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User WHERE login = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        ResultSet res = preparedStatement.executeQuery();


        if (!res.next()){
            return null;
        }
        else {
            User user = new User();
            user.setId(res.getInt("id"));
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            user.setToken(res.getString("token"));
            user.setRole(Role.of(res.getInt("role")));

            return user;
        }

    }

    public List<User> getUsers() throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User";
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

    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE User set token = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user.getToken());
        preparedStatement.setInt(2,user.getId());
        int res = preparedStatement.executeUpdate();

        return res == 1;
    }

    public User getUser(String token) throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User WHERE token = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,token);
        ResultSet res = preparedStatement.executeQuery();


        if (!res.next()){
            return null;
        }
        else {
            User user = new User();
            user.setId(res.getInt("id"));
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            user.setToken(res.getString("token"));
            user.setRole(Role.of(res.getInt("role")));

            return user;
        }

    }


}
