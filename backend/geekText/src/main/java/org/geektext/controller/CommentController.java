package org.geektext.controller;

import org.geektext.model.Book;
import org.geektext.model.Comment;
import org.geektext.model.User;
import org.geektext.service.BookService;
import org.geektext.service.CommentService;
import org.geektext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//TODO (COMMENT FEATURE)

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @PostMapping("/{isbn}/{username}/addcomment")
    public ResponseEntity<Comment> addComment(@PathVariable("isbn") long isbn,
            @PathVariable("username") String username, @RequestBody Comment comment) {
        Book book = bookService.getBookByIsbn(isbn);
        User user = userService.findUserByUsername(username);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        comment.setBook(book);
        comment.setUser(user);
        commentService.createComment(comment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment);
    }

    @GetMapping("/{isbn}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByIsbn(@PathVariable long isbn) {
        List<Comment> comments = commentService.getAllCommentsByBookIsbn(isbn);
        return ResponseEntity.ok(comments);
    }
}
