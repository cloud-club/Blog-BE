package cloudclub.blog.comment.dto;

public record CreateCommentDto(
    Long postId,
    Long sourceCommentId,
    String comments
) {
}
