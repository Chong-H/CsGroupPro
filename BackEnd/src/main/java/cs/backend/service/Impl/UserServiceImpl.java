package cs.backend.service.Impl;

import cs.backend.pojo.User;
import cs.backend.reporitoty.UserRepository;
import cs.backend.service.CryptDBServices;
import cs.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CryptDBServices cryptDBServices;
    private final String SECRET_KEY = "abcdefghabcdefgh"; // 你实际的密钥
    @Override
    public List<User> getall() throws Exception {
        List<User>users= userRepository.findAll();
        for (User user:users) {
            user.setPassword(cryptDBServices.decryptString(user.getPassword(),SECRET_KEY));
            user.setPhone(cryptDBServices.decryptString(user.getPhone(),SECRET_KEY));
        }
        return users;
    }

    @Override
    public User addUser(User user) {
        user.setCreate_time(LocalDateTime.now().toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setCreate_time(
                (userRepository.findById(user.getUserId())).get().getCreate_time()
        );
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
