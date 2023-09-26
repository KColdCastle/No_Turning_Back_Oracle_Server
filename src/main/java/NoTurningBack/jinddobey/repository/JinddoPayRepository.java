package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.JinddoPay;
import NoTurningBack.jinddobey.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JinddoPayRepository extends JpaRepository<JinddoPay, Member> {
}
