package cs.backend.reporitoty;

import cs.backend.pojo.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>, JpaSpecificationExecutor<Assignment> {

    List<Assignment> findByClazzIdOrderByDueTimeDesc(Integer clazzId);

}