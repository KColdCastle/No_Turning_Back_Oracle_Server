package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Deposit;
import NoTurningBack.jinddobey.domain.ServicePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, Long> {
}
