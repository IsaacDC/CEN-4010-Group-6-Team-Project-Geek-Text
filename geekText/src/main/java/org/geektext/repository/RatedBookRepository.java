package org.geektext.repository;

import org.geektext.model.RatedBook;
import java.util.List;

public interface RatedBookRepository {
    List<RatedBook> findRatedBooksByBookIsbn(long bookID);

    void save(RatedBook ratedBook);
}
