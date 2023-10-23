package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAdminId(String adminId);

    // ! 로구인
    Optional<Admin> findOptionalByAdminId(String adminId);

    // ! 로구인2
    Optional<Admin> findByAdminIdAndAdminPassword(String adminId, String adminPassword);

}
