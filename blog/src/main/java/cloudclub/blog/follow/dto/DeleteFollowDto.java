package cloudclub.blog.follow.dto;

import java.util.List;

public record DeleteFollowDto(
        Long srcUserId,

        List<Long> targetUserId
) {
}
