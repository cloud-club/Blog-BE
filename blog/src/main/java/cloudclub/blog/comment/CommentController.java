package cloudclub.blog.comment;

import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;
import cloudclub.blog.comment.service.CommentService;
import cloudclub.blog.global.annotation.LoginInfo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // create
    @PostMapping
    public void createComment(
        @RequestBody CreateCommentDto createCommentDto,
        @LoginInfo String userEmail
    ) {
        // createComment
        commentService.createComment(createCommentDto, userEmail);
    }

    // edit
    @PutMapping("/{commentId}")
    public void editComment(
        @PathVariable Long commentId,
        @RequestBody EditCommentDto editCommentDto,
        @LoginInfo String userEmail
    ) {
        // editComment
        commentService.editComment(editCommentDto, commentId, userEmail);
    }

    // delete
    @DeleteMapping("/{commentId}")
    public void deleteComment(
        @PathVariable Long commentId,
        @LoginInfo String userEmail
    ) {
        // deleteComment
        commentService.deleteComment(commentId, userEmail);
    }

}
