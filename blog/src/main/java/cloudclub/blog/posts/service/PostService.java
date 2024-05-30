package cloudclub.blog.posts.service;

import cloudclub.blog.posts.config.CustomException;
import cloudclub.blog.posts.config.StatusEnum;
import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.config.ResultMessage;
import cloudclub.blog.posts.entity.Post;
import cloudclub.blog.posts.repository.PostRepository;
import cloudclub.blog.posts.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostHashtagService postHashtagService;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    //게시글 등록
    public ResponseEntity<ResultMessage> save(PostRequestsDto postRequestsDto, Long userId) throws Exception {
        //예외처리

        if (postRequestsDto.title() == null) {
            throw new CustomException("Title cannot be null", "411");
        }
        if (postRequestsDto.contents() == null) {
            throw new CustomException("Content cannot be null", "412");
        }

        //게시글 저장
        Post post = postRepository.save(
                Post.builder()
                        .title(postRequestsDto.title())
                        .contents(postRequestsDto.contents())
                        .userId(userId)
                        .build()
        );
        //해시태그 저장
        postHashtagService.saveHashtag(post, postRequestsDto.tagNames());

        //response
        ResultMessage message = new ResultMessage();
        HttpHeaders headers = new HttpHeaders();

        message.setStatus(StatusEnum.OK);
        message.setData(postRequestsDto);
        message.setMessage("Post created");
        headers.set("userId", String.valueOf(userId));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
