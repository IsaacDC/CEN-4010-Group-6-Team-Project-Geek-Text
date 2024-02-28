package org.geektext.model;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "ratedbooks")
public class RatedBook {
    public RatedBook(){};
    public RatedBook(Book book, User user, int bookRating, Date datestamp) {

        this.bookID = book.getId();
        this.userID = user.getId();
        this.id = getId();
        this.bookRating = bookRating;
        this.datestamp = datestamp;
    }


    @Id
    @Column (name = "ratingnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bookID")
    private int bookID;

    @Column(name = "userID")
    private int userID;

    @Column(name = "bookRating")
    private int bookRating;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestamp")
    private Date datestamp;

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.setUserID(userID);
    }

    public int getBookID() {
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
