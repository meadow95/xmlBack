package postservice.postmodel;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findOne(String id);
    Post create(Post smestaj);
    Post update(Post smestaj);
    void delete(String id);

}
