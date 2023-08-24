package crud;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sairaghavak
 */
@Service
public class BlogService {

  @Autowired InMemStore inMemStore;

  public boolean createPost(Post post) {
    return inMemStore.createPost(post);
  }

  public Set<Post> getPosts() {
    return inMemStore.getPosts();
  }

  public Optional<Post> getPost(UUID id) {
    return inMemStore.getPost(id);
  }

  public Post updatePost(UUID id, Post post) {
    return inMemStore.updatePost(id, post);
  }

  public boolean deletePost(UUID id) {
    return inMemStore.deletePost(id);
  }

  public boolean isValidUUID(String uuid) {
    try {
      UUID.fromString(uuid);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
