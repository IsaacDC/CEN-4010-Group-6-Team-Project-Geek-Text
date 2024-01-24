package org.geektext.controller;
import org.geektext.model.RatedBook;
//import org.geektext.model.SavedBook;
import org.geektext.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/{userID}/rate/rateBook/{bookID}/{bookRating}")
    public String addRatingToBook(@PathVariable("bookID") long bookID, @PathVariable("userID") long userID,@PathVariable("bookRating") int bookRating) {

        ratingService.ratedBook(bookID,userID,bookRating);
        return "[@PostMapping] User: " + userID + " left a rating of " + bookRating + " to bookId: "+bookID;

    }
}
