package cloudclub.blog.follow.respository;

import cloudclub.blog.follow.entity.FollowInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowInfoRespository extends JpaRepository<FollowInfo, Long> {
}
