package org.geektext.service;

import java.time.LocalDateTime;
import java.util.List;

import org.geektext.model.Book;
import org.geektext.model.Comment;
import org.geektext.model.User;
import org.geektext.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Comment> getAllCommentsByBookIsbn(long bookIsbn) {
		String sql = "SELECT bookIsbn, userId, comment, datestamp FROM comments WHERE bookIsbn = ?";

		return jdbcTemplate.query(sql, (rs, rowNum) -> new Comment(
				rs.getLong("bookIsbn"),
				rs.getInt("userId"),
				rs.getString("comment"),
				rs.getTimestamp("dateTime").toLocalDateTime()), bookIsbn);
	}

	public void createComment(Comment comment) {
		String sql = "INSERT INTO comments (bookIsbn, userId, comment, datestamp) VALUES (?,?,?,?)";

		jdbcTemplate.update(sql,
				comment.getBookIsbn(),
				comment.getUserId(),
				comment.getComment(),
				comment.getDateTime()
		);
	}
}
