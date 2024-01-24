package org.geektext.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PledgeController {
    // Help Messages for accessing REST API functions


    private String helpOptions = "'/help/database', '/help/shoppingCart'";
    private String shoppingCartOptions = "'/help/shoppingCart/addBook', '/help/shoppingCart/removeBook'";
    private String databaseOptions = "'/loadUsers','/loadBooks','/loadAuthors','/showUsers','/showBooks','/showAuthors'";
    private String shoppingCartAdd = "Add Book by user/book ID ---   '/{userID}/cart/addBook/{bookID}'  ";
    private String shoppingCartRemove = "Remove Book by user/book ID --- '/{userID}/cart/remove/{bookID}";
    private String shoppingCartClear = "Remove all Books in user's cart --- /{userID}/cart/remove/all";




    @GetMapping("/")
    public String homeMsg() {
        return "GeekText Home Page - '/help' for Query options";
    }

    @GetMapping("/help")
    public String helpMsg() {
        return helpOptions;
    }

    @GetMapping("/help/database")
    public String databaseMsg() {
        return databaseOptions;
    }

    @GetMapping("/help/shoppingCart")
    public String shoppingCartMsg() {
        return shoppingCartOptions;
    }

    @GetMapping("/help/shoppingCart/addBook")
    public String shoppingCartAdd() {
        return shoppingCartAdd;
    }

    @GetMapping("/help/shoppingCart/removeBook")
    public String shoppingCartRemove() {
        return shoppingCartRemove;
    }

    @GetMapping("/help/shoppingCart/clear")
    public String shoppingCartClear() {
        return shoppingCartClear;
    }





}
