package cloudclub.blog.comment.service;

import cloudclub.blog.comment.dto.CommentCreateResDto;
import cloudclub.blog.comment.dto.CommentReadResDto;
import cloudclub.blog.comment.dto.CommentReadResDtoElement;
import cloudclub.blog.comment.dto.CreateCommentDto;
import cloudclub.blog.comment.dto.EditCommentDto;
import cloudclub.blog.comment.entity.Comment;
import cloudclub.blog.comment.repository.CommentRepository;
import cloudclub.blog.posts.entity.Post;
import cloudclub.blog.posts.repository.PostRepository;
import cloudclub.blog.users.entity.User;
import cloudclub.blog.users.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public CommentReadResDto getComment(
        String username,
        Long postId,
        Pageable pageable
    ) {
        User user = Optional.ofNullable(username)
            .flatMap(userRepository::findByEmail)
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);

        //댓글로 map을 작성
        Map<Long, CommentReadResDtoElement> commentMap = comments.stream()
            .map(comment -> new CommentReadResDtoElement(
                comment.getId(),
                comment.getUserId(),
                comment.getPostId(),
                comment.getSrcCommentId(),
                comment.getDelYn() ? "삭제된 댓글입니다" : comment.getComments(),
                new ArrayList<>()
            ))
            .collect(Collectors.toMap(CommentReadResDtoElement::commentId, comment -> comment));

        comments.forEach(comment -> {
            if (comment.getSrcCommentId() != null) {
                CommentReadResDtoElement parent = commentMap.get(comment.getSrcCommentId());
                if (parent != null) {
                    parent.commentChildren().add(commentMap.get(comment.getId()));
                }
            }
        });

        // 부모 댓글만 남기기
        List<CommentReadResDtoElement> parentComments = commentMap.values().stream()
            .filter(comment -> comment.sourceCommentId() == null)
            .toList();


        return new CommentReadResDto(
            parentComments,
            comments.getTotalElements(),
            comments.getTotalPages(),
            comments.getNumber()
        );
    }

    @Override
    public Long createComment(
        CreateCommentDto createCommentDto,
        String userEmail
    ) {
        //user 찾기
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
            user.getUserId(),
            post.getId(),
            sourceComment.getSrcCommentId(),
            createCommentDto.comments()
        );

        // comment 저장
        return commentRepository.save(newComment).getId();
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
