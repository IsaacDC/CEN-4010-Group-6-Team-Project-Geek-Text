package org.geektext.model;

import jakarta.persistence.*;
public class Book {


    private String title;
    private String author;
    private String genre;
    private String description;
    private int yearPublished;
    private int copiesSold;
    private int isbn;
    private double price;

    public Book(String title, String author, String genre, String description, int yearPublished,int copiesSold, int isbn, double price) {

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

    public String getAuthor() {
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

    public int getIsbn() {
        return isbn;
    }
    public int getCopiesSold() {
        return copiesSold;
    }
}
