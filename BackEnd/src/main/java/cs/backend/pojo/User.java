package cs.backend.pojo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;//学工号

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

//    @Column(nullable = false)
//    private String role_id;//权限，例如
    @Column(nullable = false)
    private String real_name;
    @Column(nullable = false)
    private String role;//role
    @Column(nullable = false)
    private String lastlogintime;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false )
    private String create_time;
    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public User(String username, String password, String email, String phone, String real_name, String role, String gender,String lastlogintime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.real_name = real_name;
        this.role = role;
        this.gender = gender;
        this.create_time= LocalDateTime.now().toString();
        this.lastlogintime = lastlogintime;
    }
}
