package userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import userservice.model.PostDTO;
import userservice.model.User;
import userservice.model.UserDTO;
import userservice.model.UserService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("")
@CrossOrigin("*")
public class UserController {
	
	private static final String REGISTER_USER = "http://localhost:8080/register";
	
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{email}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);
        System.out.println(user.getEmail());
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{id}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.findOne(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) == null) {
            
        	System.out.println("Pass: " + user.getPassword());
        	
        	RestTemplate restTemplate = new RestTemplate();
            
            UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), "ROLE_USER");            
            Object newUserObject = restTemplate.postForObject(REGISTER_USER, userDTO, Object.class);
        	
        	User k = userService.insert(user);
        	System.out.println("Pass: " + user.getPassword());
  
            return new ResponseEntity<User>(k, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/addPost/{user}/{post}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostDTO> addPostUser(@PathVariable("user") String user, @PathVariable("post") String post) {
    	List<User> users = new ArrayList<User>();
        users = this.userService.findAll();
        PostDTO p = new PostDTO();
        
        System.out.println("USAO SAM U METODU!!!!!!!!!!!!!!!!!!!!!!!!!!");
       
        
        for(User k : users) {

        	if(k.getUsername().equals(user)) {
        		
        		List<String> posts =  k.getPosts();
        		posts.add(post);
        		k.setPosts(posts);
        		
        		return new ResponseEntity<PostDTO>(p, HttpStatus.OK);
        		
        	}
        }
        
        return new ResponseEntity<PostDTO>(p, HttpStatus.NOT_FOUND);
    }
    /*
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/addPost/{user}"
 //           consumes = MediaType.APPLICATION_JSON_VALUE,
 //           produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addPostUser(@PathVariable("user") String user ,@RequestBody PostDTO post) {

    	List<User> users = new ArrayList<User>();
        users = this.userService.findAll();
       
        
        for(User k : users) {

        	if(k.getUsername().equals(user)) {
        		
        		List<String> posts =  k.getPosts();
        		posts.add(post.getId());
        		k.setPosts(posts);
        		
        		return new ResponseEntity<User>(k, HttpStatus.OK);
        		
        	}
        }
        
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
*/
// za dodavanje admina?
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> insertUser(@RequestBody User user) throws Exception {
        List<String> uloge = new ArrayList<String>();
        User createdKorisnik = this.userService.create(user);
        return new ResponseEntity<User>(createdKorisnik, HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users-list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<User>> getAll() {

        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users-list/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUserFromList(@PathVariable("id") String id) {

        User user = this.userService.findOne(id);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/userUsername/{username}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUserUsername(@PathVariable("username") String username) {

    	List<User> users = new ArrayList<User>();
        users = this.userService.findAll();
        
        for(User k : users) {

        	if(k.getUsername().equals(username)) {
        		
        		return new ResponseEntity<User>(k, HttpStatus.OK);
        		
        	}
        }

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        
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
