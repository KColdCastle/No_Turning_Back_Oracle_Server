package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.AdminRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
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

    public boolean login(String adminId, String adminPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findOptionalByAdminId(adminId);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            return adminPassword.equals(admin.getAdminPassword());
        }

        return false;
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

    // ! 로그인 토큰 관련
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    public String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
