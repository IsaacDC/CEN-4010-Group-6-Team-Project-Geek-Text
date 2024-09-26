package org.geektext.model;

public class Rating {
    public Rating() {
    }

    public Rating(int bookRating) {
        this.setRating(bookRating);
    }

    private int id;
    private int bookRating;

    public int getId() {
        return id;
    }

    public int getRating() {
        return bookRating;
    }

    public void setRating(int bookRating) {
        this.bookRating = bookRating;
    }
}
