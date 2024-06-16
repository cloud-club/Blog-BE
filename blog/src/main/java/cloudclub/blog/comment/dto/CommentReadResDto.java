package cloudclub.blog.comment.dto;

import java.util.List;

public record CommentReadResDto (
    List<CommentReadResDtoElement> comments,
    Long totalCommentCount,
    Integer totalPage,
    Integer currentPage
){
}
