package org.geektext.repository;

import org.geektext.model.SavedBook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedBookRepository {
      // all crud database methods

      List<SavedBook> findSavedBooksByUserID(int userID);

      void save(SavedBook newBook);

    void delete(SavedBook targetBook2);

}
