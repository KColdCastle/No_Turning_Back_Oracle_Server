package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;

import java.util.List;

public interface MemberService {
    Member informationAll(String email);

    List<Member> memberList();

    void join(Member member);

    List<Member> blackMemberListS();

    int check(String email,String password);

    Member getLogin(String email);

}
