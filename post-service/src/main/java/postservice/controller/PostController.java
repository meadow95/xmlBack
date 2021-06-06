package postservice.controller;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import javax.servlet.ServletContext;

import postservice.postmodel.PostService;
//import userservice.model.User;
import postservice.postmodel.Post;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

@RestController
//@RequestMapping("/v1/user")
@CrossOrigin("*")
public class PostController {
	
	
	@Autowired
    private PostService postService;
	
	@Autowired  ServletContext context;

	
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
            value ="/getPostsLocation/{location}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Post>> getPostsLocation(@PathVariable("location") String location) {
    	
        List<Post> posts = postService.findAll();
        
        List<Post> postsReturn = new ArrayList<Post>();
        
        for(Post k : posts) {

        	if(k.getLocation().equalsIgnoreCase(location)) {
        		
        		postsReturn.add(k);
        		
        	}
        }

        return new ResponseEntity<List<Post>>(postsReturn, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value ="/getPostsUser/{user}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Post>> getPostsUser(@PathVariable("user") String user) {
    	
    	System.out.println("Usao sam u getPostsUser");
    	
        List<Post> posts = postService.findAll();
        
        List<Post> postsReturn = new ArrayList<Post>();
        
        for(Post k : posts) {

        	if(k.getUser().equals(user)) {
        		
        		postsReturn.add(k);
        		
        	}
        }
        for(Post k : postsReturn) {

        	System.out.println("Post: " + k.getDescription());
        	
        }
        

        return new ResponseEntity<List<Post>>(postsReturn, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value ="/getPostsTag/{tag}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Post>> getPostsTag(@PathVariable("tag") String tag) {
    	
        List<Post> posts = postService.findAll();
        
        List<Post> postsReturn = new ArrayList<Post>();
        
        for(Post k : posts) {
        	
        	List<String> tags = k.getTags();
        	
            for(String t : tags) {

            	if(t.equalsIgnoreCase(tag)) {
            		
            		postsReturn.add(k);
            	}
            }
        }

        return new ResponseEntity<List<Post>>(postsReturn, HttpStatus.OK);
    }

/*
    @RequestMapping(
            method = RequestMethod.GET,
            value ="/post/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Post> getPost(@PathVariable("id") String id) {
    	Post post = this.postService.findOne(id);
        if(post == null) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        } else {
        	
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }
    }
*/

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/createPost"
 //           consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE,
 //           produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> insertPost(@RequestParam("file") MultipartFile file,
			 @RequestParam("post") String post) throws JsonParseException , JsonMappingException , Exception{

    	
    	Post postDummy = new Post();
    	
		 System.out.println("Ok .............");
	      Post pos = new ObjectMapper().readValue(post, Post.class);
	      
	      System.out.println("Opis:" + pos.getDescription());
	      System.out.println("User:" + pos.getUser());
	      
	      String user = pos.getUser();
	      Post poset = new Post();
	        // chose a Character random from this String
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz";
	  
	        // create StringBuffer size of AlphaNumericString
	        StringBuilder sb = new StringBuilder(5);
	  
	        for (int i = 0; i < 5; i++) {
	  
	            // generate a random number between
	            // 0 to AlphaNumericString variable length
	            int index
	                = (int)(AlphaNumericString.length()
	                        * Math.random());
	  
	            // add Character one by one in end of sb
	            sb.append(AlphaNumericString
	                          .charAt(index));
	        }
	        

	      System.out.println(sb);
	      
	      URL url = Paths.get("Images").toUri().toURL();
	         
	      System.out.println("Path: " + url.getPath());
	      boolean isExit = new File(url.getPath()).exists();
	      
	        if (!isExit)
	        {
	        	new File (url.getPath()).mkdir();
	        	System.out.println("mk dir.............");
	        }
	        
	        String filename = file.getOriginalFilename();
	        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	        
	        File serverFile = new File (url.getPath() +File.separator+newFileName);
	        try
	        {
	        	System.out.println("Image");
	        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
	        	 
	        	 
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }

	       
	        pos.setPic(newFileName);
	        pos.setIdentificationNum(sb.toString());
	        Post art = postService.create(pos);
	        if (art != null)
	        {
	        	return new ResponseEntity<Response>(HttpStatus.OK);
	        }
	        else
	        {
	        	return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);	
	        }
	      

    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value ="/ImgPost/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	 public byte[] getPhoto(@PathVariable("id") String id) throws Exception{
    	System.out.println("IdentificationNum je: " + id);
		 Post post = postService.findPost(id);
//		 URL url = Paths.get("Images").toUri().toURL();
		 String string = post.getIdentificationNum();
		 String stringp = post.getPic();
		 
//		 System.out.println(url.getPath()+post.getPic());
		 Path path = FileSystems.getDefault().getPath("Images", post.getPic());
		 
		 System.out.println("Putanja je: " + path);

		 return Files.readAllBytes(path);
	 }

/*
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/post/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Post> updateSmestaj(@PathVariable("id") String id, @RequestBody Post post) throws Exception{
  //  	Post foundPost = this.postService.findOne(id);

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
*/

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
