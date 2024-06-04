package cloudclub.blog.follow.entity;


import cloudclub.blog.posts.entity.BaseEntity;
import cloudclub.blog.users.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "follow_info")
public class FollowInfo extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "source_user_id")
    private User srcUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "target_user_id")
    private User targetUser;

}
