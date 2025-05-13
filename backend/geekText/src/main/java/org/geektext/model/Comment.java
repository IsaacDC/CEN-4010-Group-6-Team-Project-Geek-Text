package org.geektext.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Comment() {
    };

    public Comment(int id, String comment, User user, Book book, LocalDateTime dateTime) {

        this.id = id;
        this.comment = comment;
        this.user = user;
        this.book = book;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateTime(LocalDateTime now) {
        this.dateTime = now;
    }
}
