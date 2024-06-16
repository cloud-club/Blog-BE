package cloudclub.blog.comment.service;

import cloudclub.blog.comment.dto.CommentCreateResDto;
import cloudclub.blog.comment.dto.CommentReadResDto;
import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentReadResDto getComment(
        String username,
        Long postId,
        Pageable pageable
    );

    // create
    Long createComment(
        CreateCommentDto createCommentDto,
        String userEmail
    );

    //edit
    // create
    void editComment(
        EditCommentDto editCommentDto,
        Long commentId,
        String userEmail
    );

    //delete
    void deleteComment(
        Long commentId,
        String userEmail
    );
}
