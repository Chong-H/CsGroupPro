package cs.backend.service;

import cs.backend.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getall();
    User addUser(User user);
    User updateUser(User user);
    int deleteUser(Long id);

}
