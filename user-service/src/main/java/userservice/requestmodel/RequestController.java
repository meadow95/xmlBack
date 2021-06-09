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
        
        request.setIdentificationNum(sb.toString());
        
        System.out.println("IdenNum: " + request.getIdentificationNum());
        
        requestRepository.save(request);
        
        //requestService.newRequest(request);

        return new ResponseEntity<User>(userFound, HttpStatus.OK);
        
        
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/getRequests/{user}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Request>> getRequests(@PathVariable("user") String user)  {
    	
    	System.out.println("Usao sam u getRequests");
        User userFound = userService.findOne(user);
        
        if(requestRepository.findAll() != null) {
     
        List<Request> requests = new ArrayList<Request>();
        requests = requestRepository.findAll();
        List<Request> requestsReturn = new ArrayList<Request>();
        
        System.out.println("Zahtevi: " + requests);
        
        System.out.println("Test: " + requests);
        
        for (int i = 0; i < requests.size(); i++) {
            
            	if(requests.get(i).getReceiver().equalsIgnoreCase(user) && requests.get(i).getApproved() == 0) {
        		
            		requestsReturn.add(requests.get(i));
        		
        	}
        }
       /*
        for(Request k : requests) {

        	if(k.getReceiver().equalsIgnoreCase(user)) {
        		
        		requests.add(k);
        		
        	}
        
        }
        */
        return new ResponseEntity<List<Request>>(requestsReturn, HttpStatus.OK);
        }
        else
        	return new ResponseEntity<List<Request>>(HttpStatus.NOT_FOUND);
}
    
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/acceptRequest/{request}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> acceptRequest(@PathVariable("request") String request)  {
    	
    	System.out.println("Usao sam u prihvatanje zahteva");
    	
    	List<Request> requests = requestRepository.findAll();
    	
    	Request requestt = new Request();
    	
    	User user = new User();
    	User userToBeAdded = new User();

    	String usernameSender = "";
    	String usernameReceiver = "";
    	
        for(Request k : requests) {

        	if(k.getIdentificationNum().equalsIgnoreCase(request)) {
        		
        		usernameSender = k.getSender();
        		usernameReceiver = k.getReceiver();
        		requestt = k;
        		
        	}
        
        }
        
 
        user = userService.findOne(usernameSender);
        userToBeAdded = userService.findOne(usernameReceiver);
        	
        if(user.getFollowing() == null) {
        	
        	List<User> following = new ArrayList<User>();
        	
            following.add(userToBeAdded);
            
            user.setFollowing(following);
            
            userService.update(user);
            
            requestt.setApproved(1);
            
    		requestRepository.delete(requestt);
            requestRepository.save(requestt);
            
            return new ResponseEntity<String>(HttpStatus.OK);
        	
        	
        }
        
        else {
        	
        	List<User> following = user.getFollowing(); 
        	
            following.add(userToBeAdded);
            
            user.setFollowing(following);
            
            userService.update(user);
            
            requestt.setApproved(1);
            
    		requestRepository.delete(requestt);
            requestRepository.save(requestt);
            
            return new ResponseEntity<String>(HttpStatus.OK);
        }
   
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ignoreRequest/{request}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> ignoreRequest(@PathVariable("request") String request)  {
    	
    	System.out.println("Usao sam u odbijanje zahteva");
    	
    	List<Request> requests = requestRepository.findAll();
    	
    	Request requestt = new Request();

    	
        for(Request k : requests) {

        	if(k.getIdentificationNum().equalsIgnoreCase(request)) {
        		
        		requestt = k;
        		
        	}
        
        }
        
            requestt.setApproved(1);
            
    		requestRepository.delete(requestt);
            requestRepository.save(requestt);
            
            return new ResponseEntity<String>(HttpStatus.OK);
        
    }
}
    
