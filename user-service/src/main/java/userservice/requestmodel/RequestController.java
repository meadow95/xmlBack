package userservice.requestmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import userservice.model.User;
import userservice.model.UserService;

@RestController
@CrossOrigin("*")
public class RequestController {

    @Autowired
    private RequestService requestService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(
            method = RequestMethod.POST,
            value="/newRequest",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Request> newPoruka(@RequestBody Request request) {
    	Request newRequest = requestService.newRequest(request);
        return new ResponseEntity<Request>(newRequest, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/followPublic/{user}/{followingProfile}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> followPublic(@PathVariable("user") String user, @PathVariable("followingProfile") String followingProfile)  {
    	
    	System.out.println("Usao sam u zapracivanje javnog profila");
        User userFound = userService.findOne(user);
        
        User followingFound = userService.findOne(followingProfile);
        
        if(userFound.getFollowing() == null) {
        	
        	List<User> following = new ArrayList<User>();
        	
            following.add(followingFound);
            
            userFound.setFollowing(following);
            
            userService.update(userFound);
            
            return new ResponseEntity<User>(userFound, HttpStatus.OK);
        	
        	
        }
        
        else {
        	
        	List<User> following = userFound.getFollowing(); 
        	
            following.add(followingFound);
            
            userFound.setFollowing(following);
            
            userService.update(userFound);
            
            return new ResponseEntity<User>(userFound, HttpStatus.OK);
        }
        
        
        
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/followPrivate/{user}/{followingProfile}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> followPrivate(@PathVariable("user") String user, @PathVariable("followingProfile") String followingProfile)  {
    	
    	System.out.println("Usao sam u zapracivanje privatnog profila");
        User userFound = userService.findOne(user);

        User followingFound = userService.findOne(followingProfile);
        
        Request request = new Request(userFound.getUsername(), followingFound.getUsername());
        
        request.setApproved(0);
        
        requestRepository.insert(request);
        
        //requestService.newRequest(request);
        
        
        
        return new ResponseEntity<User>(userFound, HttpStatus.OK);
        
        
    }
}
