package crud;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * @author sairaghavak
 */
@Component
public class InMemStore {

  private Set<Post> posts = new HashSet<>();

  public boolean createPost(Post post) {
    return posts.add(post);
  }

  public Optional<Post> getPost(UUID id) {
    return Optional.ofNullable(
        posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null));
  }

  public Set<Post> getPosts() {
    return posts;
  }
  
  public Post updatePost(UUID id, Post post) {
    Post existingPost = getPost(id).orElse(null);
    if (existingPost == null) {
      return null;
    }
    existingPost.setContent(post.getContent());
    existingPost.setTitle(post.getTitle());
    existingPost.setLastModified(Instant.now());
    return existingPost;
  }

  public boolean deletePost(UUID id) {
    return posts.removeIf(post -> post.getId().equals(id));
  }
}
