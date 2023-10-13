package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.AdminRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    @Override // 로그인 세션
    public boolean login(String adminId, String adminPassword) {
        // 이메일을 사용하여 사용자 정보를 조회합니다.
        Admin admin = adminRepository.getByAdminId(adminId);
        // System.out.println("이메일:"+member.getEmail());
        // System.out.println("비밀번호: "+member.getPassword());

        // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 null을 반환합니다.
        if (admin == null || !admin.getAdminPassword().equals(adminPassword)) {
            return false;
        } else {
            return true;
        }
    }

}
