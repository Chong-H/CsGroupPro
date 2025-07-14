package cs.backend.service;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Student;

public interface StudentService {
    Student getStudentById(Integer id);

    Student addStudent(Student student);

    void deleteStudentById(Integer id);

    Student updateStudent(Integer id, Student student);
}
