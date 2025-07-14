package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Teacher;
import cs.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseMessage<Teacher> getTeacherById(@PathVariable int id) {
        try {
            Teacher t = teacherService.getTeacherById(id);
            return ResponseMessage.success(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 更新老师信息
    @PutMapping("/{id}")
    public ResponseMessage<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Teacher t = teacherService.updateTeacher(id, teacher);
        return ResponseMessage.success(t);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Teacher> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return ResponseMessage.success(null);
    }

    @PostMapping()
    public ResponseMessage<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher t = teacherService.addTeacher(teacher);
        return ResponseMessage.success(t);
    }

    @GetMapping("/list")
    public ResponseMessage<Page<Teacher>> listTeachers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String workId,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Teacher> teacherPage = teacherService.listTeachersByCriteria(name, workId, courseName, jobTitle, pageable);
        return ResponseMessage.success(teacherPage);
    }




}
