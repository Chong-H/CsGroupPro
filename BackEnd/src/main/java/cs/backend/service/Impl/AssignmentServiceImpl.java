package cs.backend.service.Impl;

import cs.backend.pojo.Assignment;
import cs.backend.reporitoty.AssignmentRepository;
import cs.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Assignment createAssignment(Assignment assignment) {
        assignment.setAssignmentId(null);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> findAssignmentsByCriteria(Integer clazzId, Integer courseId, Integer teacherId) {
        Specification<Assignment> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (clazzId != null) {
                predicates.add(criteriaBuilder.equal(root.get("clazzId"), clazzId));
            }
            if (courseId != null) {
                predicates.add(criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (teacherId != null) {
                predicates.add(criteriaBuilder.equal(root.get("teacherId"), teacherId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return assignmentRepository.findAll(spec);
    }

    @Override
    @Transactional
    public List<Assignment> updateAssignmentsByCriteria(Assignment updates, Integer clazzId, Integer courseId, Integer teacherId) {
        if (updates == null) {
            throw new IllegalArgumentException("更新内容不能为空。");
        }

        List<Assignment> assignmentsToUpdate = findAssignmentsByCriteria(clazzId, courseId, teacherId);
        if (assignmentsToUpdate.isEmpty()) {
            return new ArrayList<>();
        }

        for (Assignment assignment : assignmentsToUpdate) {
            if (updates.getTitle() != null) {
                assignment.setTitle(updates.getTitle());
            }
            if (updates.getContent() != null) {
                assignment.setContent(updates.getContent());
            }
            if (updates.getDueTime() != null) {
                assignment.setDueTime(updates.getDueTime());
            }
        }
        return assignmentRepository.saveAll(assignmentsToUpdate);
    }

    @Override
    @Transactional
    public long deleteAssignmentsByCriteria(Integer clazzId, Integer courseId, Integer teacherId) {
        List<Assignment> assignmentsToDelete = findAssignmentsByCriteria(clazzId, courseId, teacherId);
        if (assignmentsToDelete.isEmpty()) {
            return 0;
        }
        assignmentRepository.deleteAll(assignmentsToDelete);
        return assignmentsToDelete.size();
    }
}