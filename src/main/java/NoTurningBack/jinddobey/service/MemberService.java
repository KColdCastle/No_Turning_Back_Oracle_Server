package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;

import java.util.List;

public interface MemberService {
    List<Member> informationAll(String email);

    void join(Member member);

}
