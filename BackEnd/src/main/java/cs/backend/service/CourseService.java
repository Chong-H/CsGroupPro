package cs.backend.service;


import cs.backend.pojo.Course;

import java.util.List;

public interface CourseService {
    public void addCourse(Course course);
    public Course getCourse(int id);
    public List<Course> getAllCourses();
    public void deleteCourse(int id);
    public void updateCourse(Course course);

}
