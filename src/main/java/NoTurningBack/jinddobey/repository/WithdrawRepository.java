package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.ServicePrice;
import NoTurningBack.jinddobey.domain.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}
