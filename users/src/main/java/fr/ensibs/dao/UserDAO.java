package fr.ensibs.dao;

import fr.ensibs.model.User;
import fr.ensibs.util.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO class.
 *
 * @author Benoît Martel
 * @version 1.0
 */
public class UserDAO {

    /**
     * the connection instance.
     */
    private Connection connection;

    /**
     * Constructor.
     */
    public UserDAO() {

        this.connection = ConnectionSQLite.getConnection();
    }

    /**
     * This method allows you to add an user in the database.
     *
     * @param user the user to add.
     * @return the user object with the auto increment id.
     * @throws Exception throw an exception if the user already exist
     */
    public User addUser(User user) throws Exception {

        if (existUser(user.getLogin()) == null) {
            String query = "INSERT INTO User(login,password,role) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole().ordinal());
            int res = preparedStatement.executeUpdate();

            if (res == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                user.setId(rs.getInt(1));
            }

            return user;
        } else {
            throw new Exception("This user already exist !");
        }


    }

    /**
     * This method allows you to retrieve if an user exist with his login.
     *
     * @param login the login of the user
     * @return the user if retrieved, else null
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public User existUser(String login) throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        ResultSet res = preparedStatement.executeQuery();


        if (!res.next()) {
            return null;
        } else {
            User user = new User();
            user.setId(res.getInt("id"));
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            user.setToken(res.getString("token"));
            user.setRole(Role.of(res.getInt("role")));

            return user;
        }

    }

    /**
     * This method allows you to get an user from his login and his password.
     *
     * @param login    the login of the user
     * @param password the password of the user
     * @return user if the user is retrieved, else null
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public User getUser(String login, String password) throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User WHERE login = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet res = preparedStatement.executeQuery();


        if (!res.next()) {
            return null;
        } else {
            User user = new User();
            user.setId(res.getInt("id"));
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            user.setToken(res.getString("token"));
            user.setRole(Role.of(res.getInt("role")));

            return user;
        }

    }

    /**
     * This method allows you to get all users in the database.
     *
     * @return the list of users
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public List<User> getUsers() throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User";
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);

        List<User> users = new ArrayList<User>();
        while (res.next()) {
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

    /**
     * This method allows you to update an user.
     *
     * @param user the user to update
     * @return true if the user has been correctly updated
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public boolean updateUser(User user) throws SQLException {
        String query = "UPDATE User set token = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getToken());
        preparedStatement.setInt(2, user.getId());
        int res = preparedStatement.executeUpdate();

        return res == 1;
    }

    /**
     * This method allows you to get an user from an token.
     *
     * @param token the token of an user
     * @return the user or null
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public User getUser(String token) throws SQLException {
        String query = "SELECT id, login, password, token, role FROM User WHERE token = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        ResultSet res = preparedStatement.executeQuery();


        if (!res.next()) {
            return null;
        } else {
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
