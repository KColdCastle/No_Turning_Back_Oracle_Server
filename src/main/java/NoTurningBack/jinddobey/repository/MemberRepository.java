package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByEmail(String email);

}
