package cloudclub.blog.comment.service;

import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;

public interface CommentService {
    // create
     void createComment(
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
