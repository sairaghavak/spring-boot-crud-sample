package crud;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sairaghavak
 */
@RestController
public class BlogController {

  @Autowired private BlogService blogService;
  private static final String INVALID_UUID = "Invalid UUID";


  @PostMapping("/posts")
  public ResponseEntity<Post> createPost(@RequestBody Post post) {
    if (!blogService.getPost(post.getId()).isEmpty()) {
      return ResponseEntity.status(HttpStatus.CONFLICT.value()).build();
    } else if (blogService.createPost(post)) {
      return ResponseEntity.created(URI.create("/blog/posts/" + post.getId())).body(post);
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/posts/{id}")
  public ResponseEntity<?> getPost(@PathVariable("id") String id) {
    if (!blogService.isValidUUID(id)) {
      return ResponseEntity.badRequest().body(INVALID_UUID);
    }

    Post post = blogService.getPost(UUID.fromString(id)).orElse(null);
    if (post == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(post);
  }

  @GetMapping("/posts")
  public ResponseEntity<Set<Post>> getPosts() {
    Set<Post> posts = blogService.getPosts();
    return ResponseEntity.ok(posts);
  }

  @PutMapping("/posts/{id}")
  public ResponseEntity<?> updatePost(@PathVariable("id") String id, @RequestBody Post post) {
    if (!blogService.isValidUUID(id)) {
      return ResponseEntity.badRequest().body(INVALID_UUID);
    }
    Post updatedPost = blogService.updatePost(UUID.fromString(id), post);
    if (updatedPost == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedPost);
    
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") String id) {
    if (!blogService.isValidUUID(id)) {
      return ResponseEntity.badRequest().body(INVALID_UUID);
    }
    boolean isPostDeleted = blogService.deletePost(UUID.fromString(id));
    if (!isPostDeleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
    
  }
}
