package postservice.postmodel;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findOne(String id);
    Post findPost(String identificationNumber);
    Post create(Post post);
    Post update(Post post);
    void delete(String id);
    int liked(String identificationNumber);
    int disliked(String identificationNumber);


}
