package org.geektext.service;

import java.time.LocalDateTime;
import java.util.List;

import org.geektext.model.Comment;
import org.geektext.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CommentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;

	public Comment createComment(Comment comment) {
		String sql = "INSERT INTO comments (id, comment, user_id, book_id, date_time) VALUES (?,?,?,?,?)";

		comment.setDateTime(LocalDateTime.now());
		jdbcTemplate.update(sql, comment.getId(),
				comment.getComment(), comment.getUser().getId(), comment.getBook().getId(),
				comment.getDateTime());
		return comment;
	}

	public List<Comment> getAllCommentsByBookIsbn(long bookIsbn) {
		String sql = "SELECT * FROM comments WHERE bookIsbn = ?";

		return jdbcTemplate.query(sql, (rs, rowNum) -> new Comment(
				rs.getInt("id"),
				rs.getString("comment"),
				userService.findUserByUsername(rs.getString("username")),
				bookService.getBookByIsbn(rs.getLong("book_isbn")),
				rs.getTimestamp("dateTime").toLocalDateTime()), bookIsbn);
	}

}
