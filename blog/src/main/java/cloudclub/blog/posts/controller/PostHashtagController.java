package cloudclub.blog.posts.controller;

import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.service.PostHashtagService;
import cloudclub.blog.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostHashtagController {

    private final PostService postService;

    @PostMapping("/post")
    public Long updatePost(@RequestBody PostRequestsDto requestsDto) {
        return postService.save(requestsDto);
    }
}
