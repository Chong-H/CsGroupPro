package cs.backend.service.Impl;

import cs.backend.pojo.User;
import cs.backend.reporitoty.UserRepository;
import cs.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        user.setCreate_time(LocalDateTime.now().toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public int deleteUser(Long id) {
        try{
         userRepository.deleteById( id);
        }catch (Exception e){
            return 0;
        }return 1;
    }

}
