package org.geektext.repository;

import org.geektext.model.RatedBook;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface RatedBookRepository extends JpaRepositoryImplementation<RatedBook, Long> {
    List<RatedBook> findRatedBooksByBookID(long bookID);
}
