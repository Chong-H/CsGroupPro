package cs.backend.service;


import cs.backend.pojo.Student;
import cs.backend.pojo.Teacher;
import cs.backend.pojo.User;
import cs.backend.reporitoty.StudentRepository;
import cs.backend.reporitoty.TeacherRepository;
import cs.backend.reporitoty.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@SpringBootTest
public class encryptTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CryptDBServices cryptDBServices;

    private final String secretKey = "abcdefghabcdefgh"; // 和你的加密key保持一致

    /**
     * 判断字符串是否是Base64编码（简单判定）
     */
    private boolean needEncrypt(String str) {
//        if (str == null || str.isEmpty()) return false;
//        if (str.length() % 4 != 0) return true;//长度不为4，代表未加密过
//        return false;
        if(str.equals("admin")){
            return false;
        }
        return true;
    }

    /**
     * 批量加密数据库中未加密的手机号字段
     */
    @Test
    @Transactional
    @Commit
    public void encryptAllUnencryptedPhones() {
        List<Teacher> Users = teacherRepository.findAll();
        int countEncrypted = 0;
        for (Teacher t : Users) {
            User u=t.getUser();
            if (u == null) continue;
            String username = u.getUsername();
            if (username == null || username.isEmpty()) continue;
            System.out.println("原始值：" + username);
            // 如果xuyao加密
            if (needEncrypt(username)) {
                System.out.println("原始值：" + username);
                try {
                    String encrypted = cryptDBServices.decryptString(username, secretKey);
                    u.setUsername(encrypted);
                    System.out.println("加密后：" + encrypted);
                    t.setUser(u);
                    teacherRepository.save(t);
                    countEncrypted++;
                } catch (Exception e) {
                    System.err.println("加密失败，用户ID：" + u.getUserId() + "，错误：" + e.getMessage());
                }
            }
        }

        System.out.println("共加密数量：" + countEncrypted);
    }
}