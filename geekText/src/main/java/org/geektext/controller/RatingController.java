package org.geektext.controller;
import org.geektext.service.RatingService;
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
        return "[@PostMapping] User ID: " + userID + " left a rating of " + bookRating + " stars to bookId: "+bookID;

    }
    @GetMapping("/rate/averageRating/{bookID}")
    public String getAverageRatingForBook(@PathVariable("bookID") long bookID) {
        return "The average rating for bookID "+bookID+ " is "+ ratingService.calculateAverageRating(bookID);
    }
}
