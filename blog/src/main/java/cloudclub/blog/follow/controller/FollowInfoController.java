package cloudclub.blog.follow.controller;

import cloudclub.blog.common.model.ResponseDto;
import cloudclub.blog.follow.dto.CreateFollowDto;
import cloudclub.blog.follow.dto.DeleteFollowDto;
import cloudclub.blog.follow.dto.FollowInfoListDto;
import cloudclub.blog.follow.service.FollowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowInfoController {

    private final FollowInfoService followInfoService;

    @PostMapping("/follow-info")
    public ResponseEntity<ResponseDto<CreateFollowDto>> createFollowInfo(@RequestBody CreateFollowDto createFollowDto) {
        followInfoService.createFollowInfo(createFollowDto);
        ResponseDto<CreateFollowDto> responseDto = ResponseDto.<CreateFollowDto>builder()
                                                                .successOrNot(true)
                                                                .statusCode(HttpStatus.OK.toString())
                                                                .data(createFollowDto)
                                                                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/follow-info")
    public ResponseEntity<ResponseDto<FollowInfoListDto>> getFollowList(@RequestBody FollowInfoListDto reqDto) {
        FollowInfoListDto resultDto = followInfoService.getFollowInfo(Long.valueOf(reqDto.srcUserId()));       // TODO : 데이터 검증 필요. "1.0" 이런 식으로 들어오면 에러 발생함.
        ResponseDto<FollowInfoListDto> responseDto = ResponseDto.<FollowInfoListDto>builder()
                                                                .successOrNot(true)
                                                                .statusCode(HttpStatus.OK.toString())
                                                                .data(resultDto)
                                                                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/follow-info/{followInfoId}")
    public ResponseEntity<ResponseDto<DeleteFollowDto>> deleteFollowInfo(@RequestBody DeleteFollowDto deleteFollowDto) {
        followInfoService.deleteFollowInfo(deleteFollowDto);
        ResponseDto<DeleteFollowDto> responseDto = ResponseDto.<DeleteFollowDto>builder()
                .successOrNot(true)
                .statusCode(HttpStatus.OK.toString())
                .data(deleteFollowDto)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
