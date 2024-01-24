package org.geektext.service;

import org.geektext.model.Book;
import org.geektext.model.SavedBook;
import org.geektext.model.User;
import org.geektext.repository.BookRepository;
import org.geektext.repository.SavedBookRepository;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {


    @Autowired
    SavedBookRepository savedBookRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    UserRepository userRepo;




    public int addToCart(int bookID, int userID) {
        // Searches for book and user then creates new
        // "SavedBook" item to save to cart table.
        // Returns 0 if Book is not found



        Book addedBook = bookRepo.findBookById(bookID);
        if (addedBook == null) {
            return 0;
        }

        User userTarget = userRepo.findUserById(userID);
        SavedBook newBook = new SavedBook(addedBook,userTarget);
        savedBookRepo.save(newBook);
        return 1;

    }

    public double CalcSubtotal(long userID) {
        // Retrieves list of books in user's cart, loop & sum

        List<SavedBook> bookList =  savedBookRepo.findSavedBooksByUserID(userID);
        double bookPrice;
        SavedBook currBook;
        double subtotal = 0; // reset


        for (int i = 0; i < bookList.size(); i++) {

                currBook = bookList.get(i); // CHECK CAST*
                bookPrice = currBook.getPrice();
                subtotal += bookPrice;

        }

        return subtotal;
    }

    public List<SavedBook> ShowCartItems(long userID) {
        // Returns all books in users shopping cart


        List<SavedBook> savedBooks =  savedBookRepo.findSavedBooksByUserID(userID);

        return savedBooks;
    }

    public int removeFromCart( long bookID,long userID){
        // Removes specified book from cart
        // Returns 1 if successful, else returns 0

        List<SavedBook> bookList = savedBookRepo.findSavedBooksByUserID(userID);
        SavedBook targetBook2;

        if (bookList.size() < 1) {
            return 0;
        }

        for (int i = 0; i < bookList.size(); i++) {

            targetBook2 = bookList.get(i);

            if (targetBook2.getBookID() == bookID) {

                savedBookRepo.delete(targetBook2);
            }
        }
        return 1;
    }

    public void clearCart(long userID) {
        // Clears all books from specified user's cart
        List<SavedBook> bookList = savedBookRepo.findSavedBooksByUserID(userID);
        SavedBook targetBook2;

        for (int i = 0; i < bookList.size(); i++) {

            targetBook2 = bookList.get(i);
            savedBookRepo.delete(targetBook2);
        }
    }




}
