package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.User;
import cs.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseMessage<List<User>> getAllAccounts() {
        List<User> users = userService.getall();

        return ResponseMessage.success(users);
    }
    @PostMapping("/add")
    public ResponseMessage<User> addAccount(@RequestBody User user) {
        User add= userService.addUser(user);
        return ResponseMessage.success(add);
    }
    @PostMapping("/update")
    public ResponseMessage<User> updateAccount(@RequestBody User user) {
        return ResponseMessage.success(userService.updateUser(user));
    }
    @DeleteMapping("/del")
    public ResponseMessage<String> deleteAccount(@RequestBody User user) {
        try{
            userService.deleteUser(user.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseMessage.success("success");
    }

}
