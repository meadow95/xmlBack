package userservice.model;

import java.util.List;

public interface UserService{
    User findOne(String username);
    List<User> findAll();
    User insert(User k);
    User findByEmail(String email);
    User create(User user) throws Exception;
    User update(User user);

}
