package org.geektext.service;
import org.geektext.model.*;
import org.geektext.repository.BookRepository;
import org.geektext.repository.RatedBookRepository;
import org.geektext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;
@Service
public class RatingService {
    @Autowired
    RatedBookRepository ratedBookRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    UserRepository userRepo;

    public void ratedBook(int bookID, int userID, int bookRating) {


        Book rated = bookRepo.findBookById(bookID);
        User userTarget = userRepo.findUserById(userID);
        //int rate = ratedBookRepo.findRatedBooksByBookID(bookRating);

        RatedBook ratedBook = new RatedBook(rated,userTarget,bookRating,new Date());
        ratedBookRepo.save(ratedBook);

    }
    public List<RatedBook> ShowBookRatings(long bookID) {
        List<RatedBook> ratedBooks =  ratedBookRepo.findRatedBooksByBookID(bookID);

        return ratedBooks;
    }

    public double calculateAverageRating(long bookId) {
        List<RatedBook> ratedBooks = ratedBookRepo.findRatedBooksByBookID(bookId);

        if (ratedBooks.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0;
        for (RatedBook ratedBook : ratedBooks) {
            totalRating += ratedBook.getRating();
        }

        return totalRating / ratedBooks.size();
    }
}
