package NoTurningBack.jinddobey.repository;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findOptionalByAdminId(String adminId);

    Admin findByAdminId(String adminId);

}
