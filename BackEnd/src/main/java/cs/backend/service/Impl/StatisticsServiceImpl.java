package cs.backend.service.Impl;

import cs.backend.pojo.Statistics;
import cs.backend.reporitoty.ClazzRepository;
import cs.backend.reporitoty.CourseRepository;
import cs.backend.reporitoty.StudentRepository;
import cs.backend.reporitoty.TeacherRepository;
import cs.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private ClazzRepository clazzRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Statistics getAllNumber() {
        long studentCount = studentRepository.count();
        long teacherCount = teacherRepository.count();
        long courseCount = courseRepository.count();
        long clazzCount = clazzRepository.count();
        return new Statistics(studentCount,teacherCount,courseCount,clazzCount);
    }
}
