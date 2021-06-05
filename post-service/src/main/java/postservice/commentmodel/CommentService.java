package postservice.commentmodel;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findOne(String id);
    Comment create(Comment comment);
    Comment update(Comment comment) throws Exception;
    void delete(String id);
}
