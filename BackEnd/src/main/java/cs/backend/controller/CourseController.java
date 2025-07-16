package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Course;
import cs.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @PostMapping("/add")
    public ResponseMessage addCourse(@RequestBody Course course){
        try{
            courseService.addCourse(course);
            return ResponseMessage.success(course);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMessage.error(222,"add error",null);
        }
    }
    @DeleteMapping
    public ResponseMessage deleteCourse(@RequestBody Course course){
        try{
            courseService.deleteCourse(course.getCourseId());
            return ResponseMessage.success(course);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMessage.error(222,"del error",null);
        }
    }
    @PostMapping("/update")
    public ResponseMessage updateCourse(@RequestBody Course course){
        try{
            courseService.updateCourse(course);
            return ResponseMessage.success(course);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMessage.error(222,"update error",null);

        }
    }
}
