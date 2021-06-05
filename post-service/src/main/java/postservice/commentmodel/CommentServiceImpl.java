package postservice.commentmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    public List<Comment> findAll(){
        List<Comment> comment = this.commentRepository.findAll();
        return comment;
    }
/*
    public Comment findOne(String id){
    	Comment comment = this.commentRepository.findOne(id);
        return comment;
    }
*/

    public Comment create(Comment comment) {
    	Comment savedComment = this.commentRepository.insert(comment);
        return savedComment;
    }


    public Comment update(Comment comment) throws Exception {
    	Comment updateComment = this.commentRepository.save(comment);
        return updateComment;
    }


    public void delete(String id) {
        this.commentRepository.deleteById(id);
    }

	public Comment findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
