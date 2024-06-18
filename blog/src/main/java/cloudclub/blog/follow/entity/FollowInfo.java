package cloudclub.blog.follow.entity;


import cloudclub.blog.posts.entity.BaseEntity;
import cloudclub.blog.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follow_info")
@Getter
@NoArgsConstructor
public class FollowInfo extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "source_user_id")
    private User srcUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "target_user_id")
    private User targetUser;

    @Builder
    public FollowInfo(User srcUser, User targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
    }

}
