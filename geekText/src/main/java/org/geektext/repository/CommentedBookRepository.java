package org.geektext.repository;
import org.geektext.model.CommentedBook;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
public interface CommentedBookRepository extends JpaRepositoryImplementation<CommentedBook, Long>{
    List<CommentedBook> findCommentedBooksByBookID(long bookID);
}
