package cloudclub.blog.follow.dto;

public record CreateFollowDto (
        Long srcUserId,

        Long targetUserId
                              ) {
}
