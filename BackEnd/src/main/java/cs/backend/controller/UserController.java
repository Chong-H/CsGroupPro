package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.User;
import cs.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseMessage<List<User>> getAllAccounts() {//不需要id
        List<User> users = userService.getall();

        return ResponseMessage.success(users);
    }
    @PostMapping("/add")
    public ResponseMessage<User> addAccount(@RequestBody User user) {//不需要id和createtime
        try{
            User add= userService.addUser(user);
            return ResponseMessage.success(add);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @PostMapping("/update")
    public ResponseMessage<User> updateAccount(@RequestBody User user) {
        try{
             User u = userService.updateUser(user);
            return ResponseMessage.success(u);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/del")
    public ResponseMessage<String> deleteAccount(@RequestBody User user) {//需要id
        try{
            userService.deleteUser(user.getUserId());
            return ResponseMessage.success("success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("login")
    public ResponseMessage<User> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        List<User> users = userService.getall();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setLastlogintime(LocalDateTime.now().toString());
                userService.updateUser(user);
                return ResponseMessage.success(user);

            }
        }
        return ResponseMessage.error(222,"用户不存在",null);
    }

}
