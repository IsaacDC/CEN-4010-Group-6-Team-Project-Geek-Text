package org.geektext.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class RatedBook {
    @Id
    private int id;
    private long bookID;
    private int userID;
    private int bookRating;
    private Date datestamp;

    public RatedBook() {
    };

    public RatedBook(Book book, User user, int bookRating, Date datestamp) {

        this.bookID = book.getIsbn();
        this.userID = user.getId();
        this.id = getId();
        this.bookRating = bookRating;
        this.datestamp = datestamp;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.setUserID(userID);
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getRating() {
        return bookRating;
    }

    public void setRating(int bookRating) {
        this.bookRating = bookRating;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Date datestamp) {
        this.datestamp = datestamp;
    }
}
