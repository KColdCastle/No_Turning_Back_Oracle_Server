package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    // 어드민 관리자 추가시 사용
    void join(Admin admin);

    // 어드민에서 회원목록 조회시 사용
    List<Member> memberList();

    // 어드민에서 블랙리스트 조회시 사용
    List<Member> blackMemberListS();

    boolean login(String adminId, String adminPassword);

}
