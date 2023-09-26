package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<Member, String> {
}
