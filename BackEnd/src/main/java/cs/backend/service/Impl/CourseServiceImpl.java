package cs.backend.service.Impl;

import cs.backend.pojo.Course;
import cs.backend.reporitoty.CourseRepository;
import cs.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getCourse(int id) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses=courseRepository.findAll();
        return courses;
    }

    @Override
    public void deleteCourse(int id) {
         courseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }
}
