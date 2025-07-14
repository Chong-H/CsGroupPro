package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Course;
import cs.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @GetMapping("/getall")
    public ResponseMessage<List<Course>> getAllCourse(){
        try{
            List<Course> courses=courseService.getAllCourses();
            return ResponseMessage.success(courses);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMessage.error(222,"not exist",null);
        }
    }
}
