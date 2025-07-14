package cs.backend.service;

import cs.backend.pojo.Grade;
import java.util.List;

public interface GradeService {


    List<Grade> batchAddGrades(List<Grade> grades);

    List<Grade> findGradesByCriteria(Integer studentId, Integer courseId, String academicYear, Integer semester, String examType);
}