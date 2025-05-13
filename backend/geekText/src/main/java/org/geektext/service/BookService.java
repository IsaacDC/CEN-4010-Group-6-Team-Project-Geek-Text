package org.geektext.service;

import org.geektext.model.Author;
import org.geektext.model.Book;
import org.geektext.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Service
public class BookService implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthorService authorService;

    public Book addBook(Book book) {
        String sql = "INSERT INTO books (id, title, author_id, description, genre, price, year_published, isbn) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(
                sql,
                book.getId(), book.getTitle(), book.getAuthor().getId(),
                book.getDescription(), book.getGenre(),
                book.getPrice(), book.getYearPublished(), book.getIsbn());

        return book;
    }

    public List<Book> getAllBooks() {
        String str = "SELECT books.*, a.id as author_id, a.first_name, a.last_name, a.bio, a.publisher " +
                "FROM books " +
                "JOIN authors a ON books.author_id = a.id";
        return jdbcTemplate.query(str, (rs, rowNum) -> {

            Author author = new Author(
                    rs.getInt("author_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("bio"),
                    rs.getString("publisher"));

            return new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    author,
                    rs.getString("genre"),
                    rs.getString("description"),
                    rs.getInt("year_published"),
                    rs.getInt("copies_sold"),
                    rs.getLong("isbn"),
                    rs.getDouble("price"));
        });
    }

    @Override
    public Book getBookByIsbn(long isbn) {
        try {
            String str = "SELECT * FROM books WHERE isbn = ?";
            return jdbcTemplate.queryForObject(str, (rs, rosNum) -> {

                int authorId = rs.getInt("author_id");
                Author author = authorService.findAuthorById(authorId);

                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        author,
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getInt("year_published"),
                        rs.getInt("copies_sold"),
                        isbn,
                        rs.getDouble("price"));
            }, isbn);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null; // or throw an exception if preferred
        }
    }

    @Override
    public List<Book> findAllByAuthor(Author author) {
        String str = "SELECT * FROM books WHERE author_id =?";
        return jdbcTemplate.query(str, (rs, rowNum) -> new Book(
                rs.getInt("id"),
                rs.getString("title"),
                author,
                rs.getString("genre"),
                rs.getString("description"),
                rs.getInt("year_published"),
                rs.getInt("copies_sold"),
                rs.getLong("isbn"),
                rs.getDouble("price")), author.getId());
    }

}
