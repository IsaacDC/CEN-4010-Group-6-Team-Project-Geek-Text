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
    private long id;
    @Column(name = "bookID")
    private long bookID;

    @Column(name = "userID")
    private long userID;

    @Column(name = "bookRating")
    private int bookRating;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestamp")
    private Date datestamp;

    public long getId() {
        return id;
    }

    public long getUserID() {
        return userID;
    }
    public void setUserID(long userID) {
        this.setUserID(userID);
    }

    public long getBookID() {
        return bookID;
    }
    public void setBookID(long bookID) {
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
