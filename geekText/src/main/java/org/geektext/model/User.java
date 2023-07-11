package org.geektext.model;

public class User {

    public User(){}

    private String id;
    private String username;
    private String password;
    private String name;


    public User(String id, String username, String password, String name)
    {

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

}
