package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Student;
import cs.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
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
        Student s = studentService.addStudent(student);
        return ResponseMessage.success(s);
    }

    @GetMapping("/{id}")
    public ResponseMessage<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseMessage.success(student);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseMessage.success(null);
    }

    @PutMapping("/{id}")
    public ResponseMessage<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student s = studentService.updateStudent(id, student);
        return ResponseMessage.success(s);
    }
}
