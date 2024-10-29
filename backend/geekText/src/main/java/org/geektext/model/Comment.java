package org.geektext.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private long bookIsbn;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Comment() {
    };

    public Comment(long bookIsbn, int userId, String comment, LocalDateTime dateTime) {

        this.bookIsbn = bookIsbn;
        this.userId = userId;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public long getBookIsbn() {
        return bookIsbn;
    }

    public int getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
