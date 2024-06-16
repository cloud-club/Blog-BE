package cloudclub.blog.comment;

import cloudclub.blog.comment.dto.CommentCreateResDto;
import cloudclub.blog.comment.dto.CommentReadResDto;
import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;
import cloudclub.blog.comment.service.CommentService;
import cloudclub.blog.global.annotation.LoginInfo;
import cloudclub.blog.posts.config.ResultMessage;
import cloudclub.blog.posts.config.StatusEnum;
import java.security.Principal;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    //read
    @GetMapping
    public ResponseEntity<ResultMessage> getComment(
        Long postId,
        Pageable pageable,
        Principal principal
    ) {
        // getComment
        String userEmail = principal.getName();

        CommentReadResDto commentReadResDto = commentService.getComment(
            userEmail,
            postId,
            pageable
        );
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(StatusEnum.OK);
        resultMessage.setMessage("success");
        resultMessage.setData(commentReadResDto);

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<ResultMessage> createComment(
        @RequestBody CreateCommentDto createCommentDto,
        @LoginInfo String userEmail
    ) {
        // createComment
        Long commentId = commentService.createComment(createCommentDto, userEmail);
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(StatusEnum.OK);
        resultMessage.setMessage("success");
        resultMessage.setData(commentId);

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    // edit
    @PutMapping("/{commentId}")
    public ResponseEntity<ResultMessage>  editComment(
        @PathVariable Long commentId,
        @RequestBody EditCommentDto editCommentDto,
        @LoginInfo String userEmail
    ) {
        // editComment
        commentService.editComment(editCommentDto, commentId, userEmail);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(StatusEnum.OK);
        resultMessage.setMessage("success");
        resultMessage.setData(commentId);

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResultMessage> deleteComment(
        @PathVariable Long commentId,
        @LoginInfo String userEmail
    ) {
        // deleteComment
        commentService.deleteComment(commentId, userEmail);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(StatusEnum.OK);
        resultMessage.setMessage("success");

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

}
