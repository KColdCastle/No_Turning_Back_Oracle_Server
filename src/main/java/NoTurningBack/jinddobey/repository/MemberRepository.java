package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);
    List<Member> findByState(boolean state);

}
