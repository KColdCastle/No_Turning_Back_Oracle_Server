package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    // 어드민 관리자 추가시 사용
    void join(Admin admin);

    List<Admin> adminList();

    // 어드민에서 회원목록 조회시 사용
    List<Member> memberList();

    // 어드민에서 블랙리스트 조회시 사용
    List<Member> blackMemberListS();

    // ! 블랙 테스트

    public List<Member> getSuspendedMembers();

    // ! 로구인
    public Optional<Admin> findByAdminId(String adminId);

    public Optional<Admin> findAdminByAdminId(String adminId);

    public String getEmployeeNameByAdminId(String adminId);

    // ! 로구인2
    public boolean login(String adminId, String adminPassword);
//
//    // ! 토큰
//
//    public String createToken(String subject);
//
//    public Claims parseToken(String token);
}
