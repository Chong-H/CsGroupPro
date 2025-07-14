package cs.backend.reporitoty;

import cs.backend.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>, JpaSpecificationExecutor<Grade> {
}