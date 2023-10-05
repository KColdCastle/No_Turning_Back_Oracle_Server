package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealCheckRepository extends JpaRepository<Deal, String> {

}
