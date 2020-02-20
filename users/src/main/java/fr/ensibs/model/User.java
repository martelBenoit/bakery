package fr.ensibs.model;

import fr.ensibs.util.Role;

/**
 * User class.
 *
 * @author Benoît Martel
 * @version 1.0
 */
public class User {

    /**
     * the unique id
     */
    private int id;

    /**
     * the login
     */
    private String login;

    /**
     * the password
     */
    private String password;

    /**
     * the token
     */
    private String token;

    /**
     * the role
     */
    private Role role;

    /**
     * Empty constructor.
     *
     */
    public User(){

    }

    /**
     * Constructor of the user class with all parameters.
     *
     * @param id the user id
     * @param login the user login
     * @param password the user password
     * @param token the user token
     * @param role the user role
     */
    public User(int id, String login, String password, String token, Role role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    /**
     * Constructor of the user class with onlu login, password and role parameters.
     *
     * @param login the user login
     * @param password the user password
     * @param role the user role
     */
    public User(String login, String password, Role role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Get the user id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the user id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the user login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the user login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the user password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the user token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Set the user role.
     *
     * @param role the role
     */
    public void setRole(Role role){
        this.role = role;
    }

    /**
     * Get the user role.
     *
     * @return the role
     */
    public Role getRole(){
        return this.role;
    }
}

