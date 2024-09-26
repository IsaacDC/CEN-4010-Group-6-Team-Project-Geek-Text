package org.geektext.model;

import jakarta.persistence.*;

@Entity
public class SavedBook {
    // Represents Books user's have saved to shopping cart
    private double price;
    @Id
    private int id;
    private long bookID;
    private int userID;
    private int qty;

    public SavedBook() {
    }; // default constructor

    public SavedBook(Book book, User user) {

        this.bookID = book.getIsbn();
        this.userID = user.getId();
        this.id = getId();
        this.price = book.getPrice();
        this.qty = 0;

    }

    // Get+Set Methods
    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
