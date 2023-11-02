package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.AdminRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void join(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> blackMemberListS() {
        // 블랙멤버 읽어오는 메소드
        return memberRepository.findByState(false);
    }

    @Override
    public List<Admin> adminList() {
        return adminRepository.findAll();
    }

    @Override
    public Admin login(String adminId, String adminPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findOptionalByAdminId(adminId);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            // 비밀번호 검증 로직은 보안을 위해 암호화 및 해시화 필요
            if (adminPassword.equals(admin.getAdminPassword())) {
                return admin;
            }
        }

        return null;
    }

    public String getEmployeeNameByAdminId(String adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findOptionalByAdminId(adminId);

        return optionalAdmin.map(Admin::getEmployeeName).orElse(null);
    }

    // ! 블랙 테스트
    @Override
    public List<Member> getSuspendedMembers() {
        return memberRepository.findByStateFalse();
    }

    @Override
    public Optional<Admin> findByAdminId(String adminId) {
        return adminRepository.findOptionalByAdminId(adminId);
    }

    // !로구인 정보조회
    @Override
    public Optional<Admin> findAdminByAdminId(String adminId) {
        return adminRepository.findOptionalByAdminId(adminId);
    }

}
