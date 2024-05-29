package cloudclub.blog.posts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostHashtag extends BaseEntity{
    @ManyToOne
    private Post post;

    @ManyToOne
    private Hashtag hashtag;
}
