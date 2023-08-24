package crud;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * @author sairaghavak
 */
public class Post {

  private UUID id;
  private String title;
  private String content;
  private Instant lastModified;

  Post(String title, String content) {
    this.id = UUID.nameUUIDFromBytes((title + content).getBytes());
    this.title = title;
    this.content = content;
    this.lastModified = Instant.now();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public void setLastModified(Instant lastModified) {
    this.lastModified = lastModified;
  }

  public Instant getLastModified() {
    return lastModified;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content);
  }

  @Override
  public boolean equals(Object post) {
    if (this == post) {
      return true;
    }
    if (!(post instanceof Post)) {
      return false;
    }

    Post that = (Post) post;
    return this.getTitle().equals(that.getTitle()) && this.getContent().equals(that.getTitle());
  }
}
