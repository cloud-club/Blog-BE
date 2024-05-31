package cloudclub.blog.posts.service;

import cloudclub.blog.posts.config.CustomException;
import cloudclub.blog.posts.config.StatusEnum;
import cloudclub.blog.posts.dto.PostGetDto;
import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.config.ResultMessage;
import cloudclub.blog.posts.entity.Hashtag;
import cloudclub.blog.posts.entity.Post;
import cloudclub.blog.posts.entity.PostHashtag;
import cloudclub.blog.posts.repository.PostHashtagRepository;
import cloudclub.blog.posts.repository.PostRepository;
import cloudclub.blog.posts.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostHashtagService postHashtagService;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final PostHashtagRepository postHashtagRepository;
    /*
    게시글 등록
     */
    public ResponseEntity<ResultMessage> create(PostRequestsDto postRequestsDto, Long userId) throws Exception {
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

    /*
    게시글 불러오기
     */
    public ResponseEntity<ResultMessage> getPost(Long postId, Long userId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException("Post Not Found", "413"));
        List<PostHashtag> postHashtags = postHashtagRepository.findByPost(post);
        Stream<String> streamTagName = postHashtags.stream()
                .map(postHashtag -> postHashtag.getHashtag().getTagName());
        List<String> listTagName = streamTagName.collect(Collectors.toList());


        PostGetDto postGetDto = PostGetDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .tagNames(listTagName)
                .build();

        //response
        ResultMessage message = new ResultMessage();
        HttpHeaders headers = new HttpHeaders();

        message.setStatus(StatusEnum.OK);
        message.setData(postGetDto);
        message.setMessage("Post Get");
        headers.set("userId", String.valueOf(userId));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
