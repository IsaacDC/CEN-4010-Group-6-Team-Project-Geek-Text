package org.geektext.controller;
import org.geektext.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/{userID}/rate/rateBook/{bookIsbn}/{bookRating}")
    public String addRatingToBook(@PathVariable long bookIsbn, @PathVariable int userID,@PathVariable int bookRating) {

        ratingService.ratedBook(bookIsbn,userID,bookRating);
        return "[@PostMapping] User ID: " + userID + " left a rating of " + bookRating + " stars to bookIsbn: "+bookIsbn;

    }
    @GetMapping("/rate/averageRating/{bookIsbn}")
    public String getAverageRatingForBook(@PathVariable long bookIsbn) {
        return "The average rating for bookIsbn "+bookIsbn+ " is "+ ratingService.calculateAverageRating(bookIsbn);
    }
}
