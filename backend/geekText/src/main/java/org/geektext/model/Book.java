package org.geektext.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "isbn", nullable = false)
    private long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "year_published")
    private int yearPublished;

    @Column(name = "copies_sold")
    private int copiesSold;

    @Column(name = "price")
    private double price;

    public Book() {
    }

    public Book(String title, Author author, String genre, String description, int yearPublished, int copiesSold,
            long isbn, double price) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;
        this.isbn = isbn;
        this.price = price;

    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public long getIsbn() {
        return isbn;
    }

    public int getCopiesSold() {
        return copiesSold;
    }
}
