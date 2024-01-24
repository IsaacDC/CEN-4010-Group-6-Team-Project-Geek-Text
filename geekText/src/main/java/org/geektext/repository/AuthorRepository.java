package org.geektext.repository;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{


//
//    @Query("SELECT b FROM Book b WHERE b.id = :authorId")
//    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);
}


