package org.geektext.service;
import org.geektext.model.*;
import org.geektext.repository.BookRepository;
import org.geektext.repository.CommentedBookRepository;
//import org.geektext.repository.RatedBookRepository;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;
@Service
public class CommentedService {
    @Autowired
    CommentedBookRepository commentedBookRepo;
    @Autowired
    BookRepository bookRepo;
    @Autowired
    UserRepository userRepo;

    public void commentedBook(long bookID, int userID, String bookComment) {


        Book commented = bookRepo.findBookById(bookID);
        User userTarget = userRepo.findUserById(userID);


        CommentedBook commentedBook = new CommentedBook(commented,userTarget,bookComment,new Date());
        commentedBookRepo.save(commentedBook);

    }

    public List<CommentedBook> ShowBookComments(long bookID) {

        List<CommentedBook> commentedBooks =  commentedBookRepo.findCommentedBooksByBookID(bookID);

        return commentedBooks;
    }
    public List<CommentedBook> getCommentsForBook(long bookID) {
        return commentedBookRepo.findCommentedBooksByBookID(bookID);
    }
}
