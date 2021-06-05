package postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import postservice.postmodel.PostService;
import postservice.postmodel.Post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/v1/user")
@CrossOrigin("*")
public class PostController {
	
	
	@Autowired
    private PostService postService;

	
    public PostController(PostService smestajService){
        this.postService = smestajService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/posts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Post>> getAll() {
    	
        List<Post> posts = postService.findAll();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value ="/post/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Post> getSmestaj(@PathVariable("id") String id) {
    	Post post = this.postService.findOne(id);
        if(post == null) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        } else {
        	
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Post> insertPost(@RequestBody Post post) throws Exception{
    	Post createdPost  = this.postService.create(post);
        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/post/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Post> updateSmestaj(@PathVariable("id") String id, @RequestBody Post post) throws Exception{
    	Post foundPost = this.postService.findOne(id);

        if(foundPost == null){
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        Post updatePost = this.postService.update(post);
        if (updatePost == null) {
            return new ResponseEntity<Post>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Post>(updatePost, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/postDelete/{id}"
    )
    public ResponseEntity<Post> deletePost(@PathVariable("id") String id){
        this.postService.delete(id);
        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
    }
	

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> getSomeSensitiveData(HttpServletRequest request) {
        String linkWithSensitiveData = "https://youtu.be/s35rVw1zskA";
        String responseBody;

        // display link nicely in browser
        if (isRequestFromBrowser(request)) {
            responseBody = wrapLinkInHtmlTag(linkWithSensitiveData);
        } else {
            responseBody = linkWithSensitiveData;
        }

        return new ResponseEntity<String>(responseBody, HttpStatus.OK);
    }

    // check if 'Accept' header in request contains passed content type
    private boolean acceptHeaderContainsContentType(HttpServletRequest request, String contentType) {
        String acceptHeader = request.getHeader("Accept");

        if (acceptHeader == null) {
            return false;
        }

        return acceptHeader.contains(contentType);
    }

    private boolean isRequestFromBrowser(HttpServletRequest request) {
        return acceptHeaderContainsContentType(request, MediaType.TEXT_HTML.toString());
    }

    private String wrapLinkInHtmlTag(String link) {
        return String.format("<a href='%s'>%s</a>", link, link);
    }
}
