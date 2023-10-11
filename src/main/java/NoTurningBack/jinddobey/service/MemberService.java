package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;

import java.util.List;

public interface MemberService {
    //회원정보 불러올 때 사용
    Member informationAll(String email);

    //회원가입시 사용
    void join(Member member);

    //회원정보 변경시 사용
    void update(Member member, String email);

    //로그인 세션
    Member login(String email, String password);

    //어드민에서 회원목록 조회시 사용
    List<Member> memberList();

    //어드민에서 블랙리스트 조회시 사용
    List<Member> blackMemberListS();




}
