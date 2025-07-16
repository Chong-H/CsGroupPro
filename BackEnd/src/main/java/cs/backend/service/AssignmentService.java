package cs.backend.service;

import cs.backend.pojo.Assignment;
import java.util.List;

public interface AssignmentService {

    Assignment createAssignment(Assignment assignment);

    List<Assignment> findAssignmentsByCriteria(Integer clazzId, Integer courseId, Integer teacherId);

    List<Assignment> updateAssignmentsByCriteria(Assignment updates, Integer clazzId, Integer courseId, Integer teacherId);

    long deleteAssignmentsByCriteria(Integer clazzId, Integer courseId, Integer teacherId);

}