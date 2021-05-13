package xmlProjectSpringbootstarter.post;

import org.springframework.stereotype.Repository;

@Repository
public class PostDAO {

    public Post create(Post post) {
        return post;
    }
}
