package cloudclub.blog.follow.service;

import cloudclub.blog.follow.dto.CreateFollowDto;
import cloudclub.blog.follow.dto.DeleteFollowDto;
import cloudclub.blog.follow.dto.FollowInfoListDto;
import cloudclub.blog.follow.entity.FollowInfo;
import cloudclub.blog.follow.respository.FollowInfoRespository;
import cloudclub.blog.users.entity.User;
import cloudclub.blog.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowInfoService {

    private final UserRepository userRepository;
    private final FollowInfoRespository followInfoRespository;

    public FollowInfoListDto getFollowInfo(Long srcUserId) {
        List<FollowInfo> followInfoList = followInfoRespository.findFollowInfosBySrcUserId(srcUserId);
        List<Long> targetUserIdList = followInfoList.stream().map(o -> o.getTargetUser().getUserId()).collect(Collectors.toList());
        FollowInfoListDto followInfoListDto = new FollowInfoListDto(
                srcUserId,
                targetUserIdList
        );
        return followInfoListDto;
    }

    public void createFollowInfo(CreateFollowDto createFollowDto) {
        Long srcUserId = createFollowDto.srcUserId();
        Long targetUserId = createFollowDto.targetUserId();

        User srcUser =  userRepository.findById(srcUserId)
                .orElseThrow(() -> new IllegalArgumentException("src user not found")); // TODO : 없는 경우에 메시지 담고 FE 응답 나가도록 보완 필요
        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("target user not found"));  // TODO : 없는 경우에 메시지 담고 FE 응답 나가도록 보완 필요

        FollowInfo followInfo = FollowInfo.builder()
                .srcUser(srcUser)
                .targetUser(targetUser)
                .build();
        followInfoRespository.save(followInfo);
    }

    public void deleteFollowInfo(DeleteFollowDto deleteUserInfo) {
        Long srcUserId = deleteUserInfo.srcUserId();
        List<Long> targetUserIdList = deleteUserInfo.targetUserId();
        followInfoRespository.deleteFollowInfoBySrcUserId(srcUserId, targetUserIdList);
    }

}
