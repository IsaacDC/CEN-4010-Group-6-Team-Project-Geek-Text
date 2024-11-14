package org.geektext.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "bio", length = 2000)
    private String bio;
    @Column(name = "publisher", nullable = false)
    private String publisher;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName, String bio, String publisher) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.publisher = publisher;

    }

    public int getId() {
        return authorId;
    }

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
