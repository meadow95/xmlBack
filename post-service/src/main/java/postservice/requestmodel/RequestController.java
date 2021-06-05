package postservice.requestmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class RequestController {

    @Autowired
    private RequestService requestService;

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
}
