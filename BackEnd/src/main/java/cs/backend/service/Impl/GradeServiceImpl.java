package cs.backend.service.Impl;

import cs.backend.pojo.Grade;
import cs.backend.reporitoty.GradeRepository;
import cs.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;


@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> batchAddGrades(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("成绩列表不能为空。");
        }
        grades.forEach(grade -> grade.setGradeId(null));
        return gradeRepository.saveAll(grades);
    }

    @Override
    public List<Grade> findGradesByCriteria(Integer studentId, Integer courseId, String academicYear, Integer semester, String examType) {
        Specification<Grade> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (studentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("studentId"), studentId));
            }
            if (courseId != null) {
                predicates.add(criteriaBuilder.equal(root.get("courseId"), courseId));
            }
            if (StringUtils.hasText(academicYear)) {
                predicates.add(criteriaBuilder.equal(root.get("academicYear"), academicYear));
            }
            if (semester != null) {
                predicates.add(criteriaBuilder.equal(root.get("semester"), semester));
            }
            if (StringUtils.hasText(examType)) {
                predicates.add(criteriaBuilder.equal(root.get("examType"), examType));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return gradeRepository.findAll(spec);
    }
}