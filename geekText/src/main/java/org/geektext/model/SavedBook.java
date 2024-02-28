package org.geektext.model;
import jakarta.persistence.*;


@Entity
public class SavedBook {
    // Represents Books user's have saved to shopping cart

    public SavedBook(){}; // default constructor

    public SavedBook(Book book, User user) {

        this.bookID = book.getId();
        this.userID = user.getId();
        this.id = getId();
        this.price = book.getPrice();
        this.qty = 0;

    }


    private double price;

    @Id
    @Column (name = "itemnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generates incremental ID value
    private int id;

    @Column(name = "bookID")
    private int bookID;

    @Column(name = "owner")
    private int userID;

    @Column(name = "quantity")
    private int qty;





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


    public int getBookID() {
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
