package cloudclub.blog.posts.service;

import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.entity.Post;
import cloudclub.blog.posts.repository.PostRepository;
import cloudclub.blog.posts.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostHashtagService postHashtagService;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;

    //(hashtag 존재) 게시글 등록
    public Long save(PostRequestsDto postRequestsDto) {
        //게시글 저장
        Post post = postRepository.save(postRequestsDto.toEntity());
        //해시태그 저장
        postHashtagService.saveHashtag(post, postRequestsDto.tagNames());

        return post.getId();
    }

}
