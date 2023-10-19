package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    // 회원정보 불러올 때 사용
    Member informationAll(String email);

    // 회원가입시 사용
    void join(Member member);

    // 회원정보 변경시 사용
    void update(Member member, String email);

    // 로그인 세션
    boolean login(String email, String password);

    // 경고버튼
    public void increaseWarningS(String email);

    public void minusWarningS(String email);

    Optional<Member> findMemberByEmail(String email);

    List<Member> memberState(boolean state);

    List<Member> getListByNames(Member name);

    // ! 유저 검색
    List<Member> getListByEmail(Member member);

    // ! 유저 검색
    List<Member> getListByPhoneNum(Member member);
}
