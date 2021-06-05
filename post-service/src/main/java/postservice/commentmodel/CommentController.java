package postservice.commentmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import postservice.postmodel.PostService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PostService postService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }



    @RequestMapping(
            method = RequestMethod.GET,
            value ="/comments",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> comments = commentService.findAll();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/comment/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> getComment(@PathVariable("id") String id) {
    	Comment comments = this.commentService.findOne(id);
        if(comments == null){
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(comments, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/addComment/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> insertKomentar(@PathVariable ("id") String id, @RequestBody Comment comment) throws Exception{
        comment.setId(id);
        Comment createdComment  = this.commentService.create(comment);
        return new ResponseEntity<Comment>(createdComment, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/deleteComment/{id}"
    )
    public ResponseEntity<Comment> deleteKomentar(@PathVariable("id") String id){
        this.commentService.delete(id);
        return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
    }
}
