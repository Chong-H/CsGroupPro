package cs.backend.service.Impl;

import cs.backend.pojo.Student;
import cs.backend.pojo.User;
import cs.backend.reporitoty.StudentRepository;
import cs.backend.service.CryptDBServices;
import cs.backend.service.StudentService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CryptDBServices cryptDBServices;

    private final String SECRET_KEY = "abcdefghabcdefgh"; // 你实际的密钥
    @Override
    public Integer getStudentCount() {
        return studentRepository.findAll().size();
    }

    @Override
    public Student getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null && student.getUser() != null) {
            String decryptedPhone = null;
            String decryptedPassword = null;
            String decryptedusername = null;
            try {
                decryptedPhone = cryptDBServices.decryptString(student.getUser().getPhone(), SECRET_KEY);
                decryptedPassword = cryptDBServices.decryptString(student.getUser().getPassword(), SECRET_KEY);
                decryptedusername = cryptDBServices.decryptString(student.getUser().getUsername(), SECRET_KEY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            student.getUser().setPhone(decryptedPhone);
            student.getUser().setPassword(decryptedPassword);
//            student.getUser().setUsername(decryptedusername);
        }
        return student;
//        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public Student addStudent(Student student) {
        User u = student.getUser();
        if(u == null) {
            throw new IllegalArgumentException("User is null");
        }
        u.setPassword((u.getPassword() == null) || (u.getPassword() == "") ? "123456" : u.getPassword());
        u.setCreate_time(LocalDateTime.now().toString());
        u.setLastlogintime(LocalDateTime.now().toString());
        // ✅ 加密手机号
        String encryptedPhone = null;
        String encryptedPassword = null;
        String encryptedusername = null;
        try {
            encryptedPhone = cryptDBServices.encryptString(u.getPhone().toString(), SECRET_KEY);
            u.setPhone(encryptedPhone);
            encryptedPassword = cryptDBServices.encryptString(u.getPassword().toString(), SECRET_KEY);
            u.setPassword(encryptedPassword);
//            encryptedusername = cryptDBServices.encryptString(u.getUsername().toString(), SECRET_KEY);
//            u.setUsername(encryptedusername);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Integer id, Student student) throws Exception {
        Student oldStudent = studentRepository.findById(id).orElse(null);
        if(oldStudent == null) {
            throw new IllegalArgumentException("Student not found");
        }
        Student newStudent = new Student();

        BeanUtils.copyProperties(student, newStudent);
        newStudent.getUser().setCreate_time(oldStudent.getUser().getCreate_time());
        newStudent.getUser().setLastlogintime(oldStudent.getUser().getLastlogintime());

        String encryptedPhone = null;
        String encryptedPassword = null;
        String encryptedusername = null;

        encryptedPhone=cryptDBServices.encryptString(newStudent.getUser().getPhone(),SECRET_KEY);
        newStudent.getUser().setPhone(encryptedPhone);
        encryptedPassword = cryptDBServices.encryptString(newStudent.getUser().getPassword(),SECRET_KEY);
        newStudent.getUser().setPassword(encryptedPassword);
//        encryptedusername = cryptDBServices.encryptString(newStudent.getUser().getUsername(),SECRET_KEY);
//        newStudent.getUser().setUsername(encryptedusername);



        studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public Page<Student> listStudents(String name, String classId, String workId, Pageable pageable) throws Exception {
        Specification<Student> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. 根据班级ID (classId) 查询
            if (StringUtils.hasText(classId)) {
                predicates.add(criteriaBuilder.equal(root.get("classId"), classId));
            }

            // 2. 根据姓名 (name) 和 学工号 (workId) 查询
            // 这两个字段在 User 表中，所以需要进行连接 (Join)
            if (StringUtils.hasText(name) || StringUtils.hasText(workId)) {
                Join<Student, User> userJoin = root.join("user"); // 连接到 user 属性

                // 按姓名 (real_name) 模糊查询
                if (StringUtils.hasText(name)) {
                    predicates.add(criteriaBuilder.like(userJoin.get("real_name"), "%" + name + "%"));
                }
                // 按学工号 (username) 模糊查询
                if (StringUtils.hasText(workId)) {
                    predicates.add(criteriaBuilder.like(userJoin.get("username"), "%" + workId + "%"));
                }
            }

            // 将所有条件用 AND 连接起来
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        //return studentRepository.findAll(spec, pageable);
        Page<Student> page = studentRepository.findAll(spec, pageable);
        for (Student student : page.getContent()) {
            User u = student.getUser();
            if (u != null) {
                String decryptedPhone = cryptDBServices.safeDecrypt(u.getPhone(), SECRET_KEY);
                u.setPhone(decryptedPhone);
                String decryptedPassword = cryptDBServices.safeDecrypt(u.getPassword(), SECRET_KEY);
                u.setPassword(decryptedPassword);
//                String decryptedusername = cryptDBServices.safeDecrypt(u.getUsername(), SECRET_KEY);
//                u.setUsername(decryptedusername);
            }
        }
        return page;
    }

    @Override
    public Integer getStudentclazzById(String name) throws Exception {
        List<Student> s =studentRepository.findAll();
        for (Student student : s) {
            if(student.getUser().getUsername().equals(name)) {
                return Integer.valueOf(student.getClassId());
            }
        }
        return -1;
    }


}
