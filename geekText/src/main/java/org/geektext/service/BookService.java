package org.geektext.service;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.geektext.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Service
public class BookService implements BookRepository {
    private JdbcTemplate jdbcTemplate;
    private AuthorService authorService;

    public BookService(JdbcTemplate jdbcTemplate, AuthorService AuthorService) {
    }

    public void addBook(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (title, authorId, genre, description, year_published, copies_sold, isbn, price) VALUES (?,?,?,?,?,?,?,?)",
                book.getTitle(), book.getAuthor().getId(), book.getGenre(), book.getDescription(),
                book.getYearPublished(),
                book.getCopiesSold(), book.getIsbn(), book.getPrice());
    }

    @Override
    public Book findBookByIsbn(long isbn) {
        try {
            String str = "SELECT FROM books WHERE isbn = ?";

            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> {

                int authorId = rs.getInt("author_id");
                Author author = authorService.findAuthorById(authorId);

                return new Book(
                        rs.getString("title"),
                        author,
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("year_published"),
                        rs.getInt("copies_sold"),
                        rs.getLong("isbn"),
                        rs.getDouble("price"));
            }, isbn);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> findAllByAuthor(Author author) {
        String str = "SELECT * FROM books WHERE author =?";
        return jdbcTemplate.query(str, (rs, rowNum) -> new Book(rs.getString("title"),
                author,
                rs.getString("genre"),
                rs.getString("description"),
                rs.getInt("year_published"),
                rs.getInt("copies_sold"),
                rs.getLong("isbn"),
                rs.getDouble("price")), author.getId());
    }

}
