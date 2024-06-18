package cloudclub.blog.follow.respository;

import cloudclub.blog.follow.entity.FollowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowInfoRespository extends JpaRepository<FollowInfo, Long> {

    @Modifying
    @Query("delete from FollowInfo f where f.srcUser.userId = :srcId and f.targetUser.userId in :targetIds")
    void deleteFollowInfoBySrcUserId(@Param("srcId") Long srcId, @Param("targetIds") List<Long> targetIds);

    @Query("select f from FollowInfo f where f.srcUser.userId = :id")
    List<FollowInfo> findFollowInfosBySrcUserId(@Param("id") Long id);

}
