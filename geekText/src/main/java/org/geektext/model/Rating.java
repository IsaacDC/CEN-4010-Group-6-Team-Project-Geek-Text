package org.geektext.model;
import jakarta.persistence.*;
@Entity
@Table(name = "rating")
public class Rating {
    public Rating() {}

    public Rating(int bookRating){
        this.setRating(bookRating);
    }
    @Id
    private long id;
    private int bookRating;
    public long getId() {
        return id;
    }
    public int getRating(){return bookRating;}
    public void setRating(int bookRating){this.bookRating=bookRating;}
}
