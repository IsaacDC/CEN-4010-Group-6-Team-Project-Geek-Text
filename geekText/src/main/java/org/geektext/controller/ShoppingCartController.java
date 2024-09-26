package org.geektext.controller;

import org.geektext.model.SavedBook;
import org.geektext.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cartService;

    @PostMapping("/{userID}/cart/addBook/{bookIsbn}")
    public String addBookToCart(@PathVariable int bookIsbn, @PathVariable int userID) {
        // Adds book to user's cart by bookIsbn

        if (cartService.addToCart(bookIsbn, userID) < 1) {
            return "Error - Invalid bookIsbn";
        } else {
            return "[@PostMapping] Book ID: " + bookIsbn + " added to User ID: " + userID + " shopping cart.";
        }
    }

    @GetMapping("/{userID}/cart/books")
    public List<SavedBook> showBooksInCart(@PathVariable int userID) {
        // Return all books in user's cart

        return cartService.ShowCartItems(userID);

    }

    @GetMapping("/{userID}/cart/subtotal")
    public double Subtotal(@PathVariable int userID) {
        // Re-calculates and returns subtotal of all books saved in cart

        return cartService.CalcSubtotal(userID);

    }

    @DeleteMapping("/{userID}/cart/removeBook/{bookIsbn}")
    public String RemoveBook(@PathVariable int userID, @PathVariable long bookIsbn) {
        // Remove book from user's cart using bookIsbn

        if (cartService.removeFromCart(bookIsbn, userID) > 0) {
            return "[@DeleteMapping] Book ID: " + bookIsbn + " removed from User ID: " + userID + " shopping cart.";
        } else {
            return "Error - [bookIsbn: " + bookIsbn + "] not found in [UserID: " + userID + "] cart.";
        }
    }

    @DeleteMapping("/{userID}/cart/clear")
    public String RemoveAll(@PathVariable int userID) {
        // Remove all books from user's cart

        cartService.clearCart(userID);

        return "[@DeleteMapping] - Shopping cart emptied for User ID: " + userID;
    }

}
