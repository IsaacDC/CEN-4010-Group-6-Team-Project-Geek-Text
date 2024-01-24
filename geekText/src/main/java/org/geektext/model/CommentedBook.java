package org.geektext.model;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "commentedbooks")
public class CommentedBook {
    public CommentedBook(){};
    public CommentedBook(Book book, User user, String comment, Date datestamp) {

        this.bookID = book.getId();
        this.userID = user.getId();
        this.id = getId();
        this.comment = comment;
        this.datestamp = datestamp;
    }
    @Id
    @Column (name = "commentnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bookID")
    private long bookID;

    @Column(name = "userID")
    private long userID;

    @Column(name = "Comment")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datestamp")
    private Date datestamp;

    public long getId() {
        return id;
    }
    public long getBookID() {
        return bookID;
    }
    public void setBookID(long bookID) {
        this.bookID = bookID;
    }
    public long getUserID() {
        return userID;
    }
    public void setUserID(long userID) {
        this.setUserID(userID);
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String bookComment) {
        this.comment = comment;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Date datestamp) {
        this.datestamp = datestamp;
    }
}
