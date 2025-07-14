package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Student;
import cs.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*
    * {
    "birthDate": "2005-10-20",
    "status": 1,
    "classId": "CS-A2",
    "user": {
        "username": "S2025001",
        "password": "testpassword",
        "email": "test.student@example.com",
        "phone": "13912345678",
        "real_name": "王六",
        "gender": "女",
        "role": "student"
    }
}
    * */
    @PostMapping("/add")
    public ResponseMessage<Student> addStudent(@RequestBody Student student) {
        try {
            Student s = studentService.addStudent(student);
            return ResponseMessage.success(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseMessage<Student> getStudentById(@PathVariable Integer id) {
        try {
            Student student = studentService.getStudentById(id);
            return ResponseMessage.success(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteStudentById(@PathVariable Integer id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseMessage.success(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseMessage<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Student s = studentService.updateStudent(id, student);
            return ResponseMessage.success(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    public ResponseMessage<Page<Student>> listStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String classId,
            @RequestParam(required = false) String workId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        try {
            Page<Student> studentPage = studentService.listStudents(name, classId, workId, pageable);
            return ResponseMessage.success(studentPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
