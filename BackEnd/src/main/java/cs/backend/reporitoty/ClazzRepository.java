package cs.backend.reporitoty;

import cs.backend.pojo.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
}
