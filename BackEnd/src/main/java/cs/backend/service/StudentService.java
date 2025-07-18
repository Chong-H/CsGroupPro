package cs.backend.service;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Integer getStudentCount();

    Student getStudentById(Integer id);

    Student addStudent(Student student);

    void deleteStudentById(Integer id);

    Student updateStudent(Integer id, Student student) throws Exception;

    Page<Student> listStudents(String name, String classId, String workId, Pageable pageable) throws Exception;

    Integer getStudentclazzById(String name) throws Exception;
}
