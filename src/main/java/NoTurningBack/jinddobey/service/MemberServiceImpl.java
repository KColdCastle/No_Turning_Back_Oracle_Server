package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member informationAll(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void join(Member member) {
        // 회원가입
        memberRepository.save(member);
    }

    @Override // 로그인 세션
    public boolean login(String email, String password) {
        // 이메일을 사용하여 사용자 정보를 조회합니다.
        Member member = memberRepository.findByEmail(email);
        // System.out.println("이메일:"+member.getEmail());
        // System.out.println("비밀번호: "+member.getPassword());

        // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 null을 반환합니다.
        if (member == null || !member.getPassword().equals(password)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void update(Member member, String email) {
        Member member1 = memberRepository.findByEmail(email);
        System.out.println(member.getEmail());
        System.out.println(member.getPassword());
        member1.setPassword(member.getPassword());
        memberRepository.save(member1);
    }

    @Override
    public List<Member> memberState(boolean state) { // 매개변수 사용 수정
        return memberRepository.findByState(state); // 여기서 state 값을 정확하게 전달
    }

    @Override
    public void increaseWarningS(String email) {
        Member member = memberRepository.findOptionalByEmail(email).orElse(null);
        if (member == null)
            return;

        if (member.getWarning() < 3) {
            member.setWarning(member.getWarning() + 1);
            member.setState(false);
        }
        if (member.getWarning() == 3) {
            member.setState(true);
        }
        memberRepository.save(member);
    }

    @Override
    public void minusWarningS(String email) {
        Member member = memberRepository.findOptionalByEmail(email).orElse(null);

        if (member == null) {
            return;
        }
        if (member.getWarning() > 0) {
            member.setWarning(member.getWarning() - 1);
            member.setState(false);
        }
        memberRepository.save(member);

    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findOptionalByEmail(email);
    }

    @Override
    public List<Member> getListByNames(Member name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListByNames'");
    }

    // ! 유저 검색 이메일
    @Override
    public List<Member> getListByEmail(Member member) {
        return memberRepository.findByEmailContaining(member.getEmail());

    }

    // ! 유저 검색 폰
    @Override
    public List<Member> getListByPhoneNum(Member member) {
        return memberRepository.findByPhoneNumContaining(member.getPhoneNum());

    }

}
