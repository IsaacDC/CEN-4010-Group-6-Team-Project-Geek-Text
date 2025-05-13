package org.geektext.repository;

import java.util.List;

import org.geektext.model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

    Comment createComment(Comment comment);

    List<Comment> getAllCommentsByBookIsbn(long bookIsbn);
}
