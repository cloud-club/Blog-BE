package cloudclub.blog.comment.dto;

import java.util.List;

public record CommentReadResDtoElement(
    Long commentId,
    Long userId,
    Long postId,
    Long sourceCommentId,
    String comments,
    List<CommentReadResDtoElement> commentChildren
){
}