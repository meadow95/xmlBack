package userservice.requestmodel;

import java.util.List;

import userservice.model.User;

public interface RequestService {
	Request newRequest(Request request);
	List<Request> findAll();
	Request update(Request request);
}
