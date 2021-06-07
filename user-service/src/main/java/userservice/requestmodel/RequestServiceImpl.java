package userservice.requestmodel;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RequestServiceImpl implements RequestService{

    private RequestRepository requestRepository;

    public Request newRequest(Request request) {
    	
    	System.out.println("Info: " + request.getReceiver() + " " + request.getSender());
    	return requestRepository.insert(request);
    }

}
