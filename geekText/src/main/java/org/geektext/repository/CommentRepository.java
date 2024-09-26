package org.geektext.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.geektext.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

    void createComment(Comment comment, int userId, long bookIsbn, LocalDateTime dateTime);

    List<Comment> getAllCommentsByBookIsbn(long bookIsbn);
}