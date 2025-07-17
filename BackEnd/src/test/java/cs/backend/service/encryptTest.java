package cs.backend.service;


import cs.backend.pojo.Student;
import cs.backend.pojo.User;
import cs.backend.reporitoty.StudentRepository;
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
    private CryptDBServices cryptDBServices;

    private final String secretKey = "abcdefghabcdefgh"; // 和你的加密key保持一致

    /**
     * 判断字符串是否是Base64编码（简单判定）
     */
    private boolean needEncrypt(String str) {
        if (str == null || str.isEmpty()) return false;
        if (str.length() % 4 != 0) return true;//长度不为4，代表未加密过
        return false;
    }

    /**
     * 批量加密数据库中未加密的手机号字段
     */
    @Test
    @Transactional
    @Commit
    public void encryptAllUnencryptedPhones() {
        List<User> Users = userRepository.findAll();
        int countEncrypted = 0;

        for (User u : Users) {

            if (u == null) continue;

            String phone = u.getPhone();
            if (phone == null || phone.isEmpty()) continue;
            System.out.println("手机号原始值：" + phone);
            // 如果xuyao加密
            if (needEncrypt(phone)) {
                System.out.println("手机号原始值：" + phone);
                try {
                    String encryptedPhone = cryptDBServices.encryptString(phone, secretKey);
                    u.setPhone(encryptedPhone);
                    System.out.println("手机号：" + encryptedPhone);
                    userRepository.save(u);
                    countEncrypted++;
                } catch (Exception e) {
                    System.err.println("加密手机号失败，用户ID：" + u.getUserId() + "，错误：" + e.getMessage());
                }
            }
        }

        System.out.println("共加密手机号数量：" + countEncrypted);
    }
}