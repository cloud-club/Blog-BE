package cloudclub.blog.posts.repository;

import cloudclub.blog.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
