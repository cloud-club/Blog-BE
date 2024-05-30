package cloudclub.blog.posts.controller;

import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.config.ResultMessage;
import cloudclub.blog.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/v1/post")
    public ResponseEntity<ResultMessage> updatePost(@RequestBody PostRequestsDto requestsDto, @RequestHeader Long userId) throws Exception {
        return postService.save(requestsDto, userId);
    }
}
