package org.geektext.model;

import jakarta.persistence.*;


public class Author {

    private int id;
    private String firstName;
    private String lastName;
    private String bio;
    private String publisher;
    public Author(String firstName, String lastName, String bio, String publisher, int id) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.publisher = publisher;

    }
    public int getId() {return id;}
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBio() {
        return bio;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setFirstName(String firstName) {
    }
}



