package org.geektext.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String fullname;
    private String password;
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CreditCard creditCard;

    public User(){}

    public User(int id, String address, String fullname, String password, String username) {
        this.id = id;
        this.address = address;
        this.fullname = fullname;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}