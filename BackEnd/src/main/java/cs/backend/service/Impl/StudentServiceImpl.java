package cs.backend.service.Impl;

import cs.backend.pojo.Student;
import cs.backend.pojo.User;
import cs.backend.reporitoty.StudentRepository;
import cs.backend.reporitoty.UserRepository;
import cs.backend.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
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
        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Integer id, Student student) {
        Student oldStudent = studentRepository.findById(id).orElse(null);
        if(oldStudent == null) {
            throw new IllegalArgumentException("Student not found");
        }
        Student newStudent = new Student();
        BeanUtils.copyProperties(student, newStudent);
        newStudent.getUser().setCreate_time(oldStudent.getUser().getCreate_time());
        newStudent.getUser().setLastlogintime(oldStudent.getUser().getLastlogintime());


        studentRepository.save(newStudent);
        return newStudent;
    }


}
