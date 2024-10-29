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
                "INSERT INTO books (isbn, copies_sold, description, genre, price, title, year_published, author_id) VALUES (?,?,?,?,?,?,?,?)",
                book.getIsbn(), book.getCopiesSold(), book.getDescription(), book.getGenre(),
                book.getPrice(),
                book.getTitle(), book.getYearPublished(), book.getAuthor().getId());
    }

    @Override
    public Book findBookByIsbn(long isbn) {
        try {
            String str = "SELECT FROM books WHERE isbn = ?";

            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> {

                int authorId = rs.getInt("author_id");
                Author author = authorService.findAuthorById(authorId);

                return new Book(
                        rs.getString("isbn"),
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
        String str = "SELECT * FROM books WHERE author_id =?";
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
