package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Deal, Integer> {
}
