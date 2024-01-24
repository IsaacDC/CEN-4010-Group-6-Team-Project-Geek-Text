package org.geektext.controller;
import org.geektext.model.CommentedBook;
//import org.geektext.services.CommentedService;
import org.geektext.services.CommentedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class CommentingController {

    @Autowired
    CommentedService commentingService;

    @PostMapping("/{userID}/comment/{comment}/{bookID}")
    public String addRatingToBook(@PathVariable("bookID") long bookID, @PathVariable("userID") long userID,@PathVariable("comment") String comment) {

        commentingService.commentedBook(bookID,userID,comment);
        return "[@PostMapping] User ID: " + userID + " commented: " + comment + ", to bookID "+bookID;

    }
    @GetMapping("/comments/{bookID}")
    public List<CommentedBook> getCommentsForBook(@PathVariable("bookID") long bookID) {
        return commentingService.getCommentsForBook(bookID);
    }
}
