package cloudclub.blog.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "comments")
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private Long userId;

    private Long postId;

    private Long srcCommentId;

    private Boolean delYn;

    private String comments;

    @Builder
    public Comment(Long userId, Long postId, Long srcCommentId, String comments) {
        this.userId = userId;
        this.postId = postId;
        this.srcCommentId = srcCommentId;
        this.comments = comments;
        this.delYn = false;
    }

    public void editComment(String comments) {
        this.comments = comments;
    }

    public void deleteComment() {
        this.delYn = true;
    }
}
