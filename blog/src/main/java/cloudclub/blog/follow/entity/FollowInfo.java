package cloudclub.blog.follow.entity;


import cloudclub.blog.posts.entity.BaseEntity;
import cloudclub.blog.users.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "follow_info")
@Getter
public class FollowInfo extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User srcUser;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User targetUser;
}
