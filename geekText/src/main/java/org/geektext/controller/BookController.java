package org.geektext.controller;

import org.geektext.model.Book;
import org.geektext.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return new ResponseEntity<>("Book Loaded to Database", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/show/{isbn}")
    public ResponseEntity<Book> showBookByIsbn(@PathVariable("isbn") int isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}

//        @GetMapping("/authors/{authorId}/books")
//    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("authorId") Long authorId) {
////        try{
////            Book books = bookService.getAuthorsBook(authorId);
//
//
//        List<Book> books = authorService.getBooksByAuthorId(authorId);
//        if (books.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
////        }catch(Exception e){
////                System.out.println(e);
////                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////
////            }
//        }
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }
//
//}
