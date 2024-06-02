package cloudclub.blog.posts.dto;

import cloudclub.blog.posts.entity.Hashtag;
import cloudclub.blog.posts.entity.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Builder
@Getter
@Setter
public class PostGetDto {
    String title;
    String contents;
    List<String> tagNames;
}
