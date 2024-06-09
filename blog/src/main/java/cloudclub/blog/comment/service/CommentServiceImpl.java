package cloudclub.blog.comment.service;

import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;
import cloudclub.blog.comment.entity.Comment;
import cloudclub.blog.comment.repository.CommentRepository;
import cloudclub.blog.posts.entity.Post;
import cloudclub.blog.posts.repository.PostRepository;
import cloudclub.blog.users.entity.User;
import cloudclub.blog.users.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void createComment(
        CreateCommentDto createCommentDto,
        String userEmail
    ) {
        //user 찾기
        Long userId = 1L;
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        // post 찾기
        Post post = postRepository.findById(createCommentDto.postId())
            .orElseThrow(() -> new IllegalArgumentException("post not found"));

        // sourceComment 찾기 -> 없으면 null
        Comment sourceComment = Optional.ofNullable(createCommentDto.sourceCommentId())
            .map(id -> commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("source comment not found")))
            .orElse(null);

        // comment가 null이 아니면 id가 postId와 같은지 확인
        Optional.ofNullable(sourceComment)
            .filter(comment -> comment.getPostId().equals(createCommentDto.postId()))
            .orElseThrow(() -> new IllegalArgumentException("source comment and post not match"));

        // comment 생성
        Comment newComment = new Comment(
            userId,
            post.getId(),
            sourceComment.getSrcCommentId(),
            createCommentDto.comments()
        );

        // comment 저장
        commentRepository.save(newComment);
    }

    @Override
    public void editComment(
        EditCommentDto editCommentDto,
        Long commentId,
        String userEmail
    ){
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("comment not found"));

        if(!comment.getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("user not match");
        }

        comment.editComment(editCommentDto.comments());
    }

    @Override
    public void deleteComment(
        Long commentId,
        String userEmail
    ){
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("comment not found"));

        if(!comment.getUserId().equals(user.getUserId())){
            throw new IllegalArgumentException("user not match");
        }

        comment.deleteComment();
    }
}
