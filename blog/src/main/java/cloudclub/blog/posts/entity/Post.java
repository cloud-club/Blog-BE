package cloudclub.blog.posts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(name = "url")
    private String url;

    @Column(name = "thumbnail_image")
    private String thumbImg;

    @Column(name = "ogTitle")
    private String ogTitle;

    @Column(name = "ogDescription")
    private String ogDescription;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PostHashtag> postHashtags;

    @Builder
    public Post(String title, String contents, Long userId, String url) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.url = url;
    }


//    //여기 아래로는 사용 X
//    @Column(name = "view_count")
//    private Long viewCount;
//
//    @Column(name = "del_yn")
//    private String delYn;


}
