package fr.ensibs.models;

import fr.ensibs.util.Role;

public class User {

    private int id;

    private String login;

    private String password;

    private String token;

    private Role role;

    public User(){

    }

    public User(int id, String login, String password, String token, Role role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public User(String login, String password, Role role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return this.role;
    }
}
