package cloudclub.blog.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private Long userId;

    private String email;

    private String password;

    private String name;

    @Column(name = "provider_type")
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String introduction;

    @Column(name = "profile_img")
    private String profile;

    @Column(name = "user_role")
    private String role;

    @Column(name = "del_yn")
    private Boolean isDeleted;

    @Column(name = "github_link")
    private String github;

    @Column(name = "linkedin_link")
    private String linkedin;

    @Column(name = "insta_link")
    private String instagram;

    private String stack;

    private String position;
}
