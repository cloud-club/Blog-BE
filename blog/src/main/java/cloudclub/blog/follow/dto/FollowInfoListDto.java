package cloudclub.blog.follow.dto;

import java.util.List;

public record FollowInfoListDto(
        Long srcUserId,
        List<Long> targetUserId
) {
}
