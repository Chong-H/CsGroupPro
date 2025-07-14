package cs.backend.service;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface TeacherService {
    Teacher getTeacherById(int id);

    Teacher updateTeacher(int id, Teacher teacher);

    void deleteTeacher(int id);

    Teacher addTeacher(Teacher teacher);

    Page<Teacher> listTeachersByCriteria(String name, String workId, String courseName, String jobTitle, Pageable pageable);
}
