package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Deal;
import NoTurningBack.jinddobey.domain.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
