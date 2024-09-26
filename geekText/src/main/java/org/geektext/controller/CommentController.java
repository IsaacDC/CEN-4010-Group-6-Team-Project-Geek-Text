package org.geektext.controller;

import org.geektext.model.Comment;
import org.geektext.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<String> addComment(@RequestBody Comment commentRequest) {
        try {
            Comment comment = new Comment(
                    commentRequest.getBookIsbn(),
                    commentRequest.getUserId(),
                    commentRequest.getComment(),
                    commentRequest.getDateTime());

            // Add the comment via the service
            commentService.createComment(comment);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body("Failed to add comment.");
        }
    }

    @GetMapping("/{isbn}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByIsbn(@PathVariable long isbn) {
        List<Comment> comments = commentService.getAllCommentsByBookIsbn(isbn);
        return ResponseEntity.ok(comments);
    }
}
