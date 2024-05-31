package cloudclub.blog.posts.controller;

import cloudclub.blog.posts.dto.PostRequestsDto;
import cloudclub.blog.posts.config.ResultMessage;
import cloudclub.blog.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/v1/post")
    public ResponseEntity<ResultMessage> createPost(@RequestBody PostRequestsDto requestsDto, @RequestHeader Long userId) throws Exception {
        return postService.create(requestsDto, userId);
    }

    @GetMapping("/v1/post")
    public ResponseEntity<ResultMessage> getPost(@RequestParam Long postId, @RequestHeader Long userId) {
        return postService.getPost(postId, userId);
    }
}
