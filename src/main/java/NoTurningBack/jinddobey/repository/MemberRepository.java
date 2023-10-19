package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByState(boolean state);

    Optional<Member> findOptionalByEmail(String email);

    Member findByEmail(String email);

    // ! 유저 검색
    List<Member> findByEmailContaining(String email);

    // ! 유저 검색
    List<Member> findByPhoneNumContaining(String phoneNum);

}
