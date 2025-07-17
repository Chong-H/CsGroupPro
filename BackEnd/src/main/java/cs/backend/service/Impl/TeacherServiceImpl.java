package cs.backend.service.Impl;


import cs.backend.pojo.Student;
import cs.backend.pojo.Teacher;
import cs.backend.pojo.User;
import cs.backend.reporitoty.TeacherRepository;
import cs.backend.service.CryptDBServices;
import cs.backend.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CryptDBServices cryptDBServices;
    private final String SECRET_KEY = "abcdefghabcdefgh"; // 你实际的密钥

    @Override
    public Teacher getTeacherById(int id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null && teacher.getUser() != null) {
            try {
                String decryptedPhone = cryptDBServices.decryptString(teacher.getUser().getPhone(), SECRET_KEY);
                teacher.getUser().setPhone(decryptedPhone);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return teacher;
    }


    @Override
    public Teacher updateTeacher(int id, Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findById(id).orElse(null);
        if(oldTeacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }
        Teacher newTeacher = new Teacher();
        BeanUtils.copyProperties(teacher, newTeacher);
        newTeacher.getUser().setCreate_time(oldTeacher.getUser().getCreate_time());
        newTeacher.getUser().setLastlogintime(oldTeacher.getUser().getLastlogintime());

        try {
            String encryptedPhone = cryptDBServices.encryptString(newTeacher.getUser().getPhone(), SECRET_KEY);
            newTeacher.getUser().setPhone(encryptedPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        teacherRepository.save(newTeacher);
        return newTeacher;
    }

    @Override
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        User u = teacher.getUser();
        if(u == null) {
            throw new IllegalArgumentException("User is null");
        }
        u.setPassword((u.getPassword() == null) || (u.getPassword() == "") ? "123456" : u.getPassword());
        u.setCreate_time(LocalDateTime.now().toString());
        u.setLastlogintime(LocalDateTime.now().toString());
        try {
            String encryptedPhone = cryptDBServices.encryptString(u.getPhone(), SECRET_KEY);
            u.setPhone(encryptedPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public Page<Teacher> listTeachersByCriteria(String name, String workId, String courseName, String jobTitle, Pageable pageable) {
        Specification<Teacher> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 根据姓名模糊查询
            if (StringUtils.hasText(name)) {
                predicates.add(criteriaBuilder.like(root.get("user").get("real_name"), "%" + name + "%"));
            }

            // 根据 work_id 查询
            if (StringUtils.hasText(workId)) {
                predicates.add(criteriaBuilder.like(root.get("user").get("username"), "%" + workId + "%"));
            }

            // 根据 courseName 模糊查询
            if (StringUtils.hasText(courseName)) {
                predicates.add(criteriaBuilder.like(root.get("courseName"), "%" + courseName + "%"));
            }

            // 根据 jobTitle 模糊查询
            if (StringUtils.hasText(jobTitle)) {
                predicates.add(criteriaBuilder.like(root.get("jobTitle"), "%" + jobTitle + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<Teacher> page = teacherRepository.findAll(spec, pageable);

        // 解密手机号
        for (Teacher teacher : page.getContent()) {
            User u = teacher.getUser();
            if (u != null) {
                try {
                    String decryptedPhone = cryptDBServices.decryptString(u.getPhone(), SECRET_KEY);
                    u.setPhone(decryptedPhone);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return page;
    }
}
