package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        //회원가입
        memberRepository.save(member);
    }

    @Override //로그인 세션
    public boolean login(String email, String password) {
        // 이메일을 사용하여 사용자 정보를 조회합니다.
        Member member = memberRepository.findByEmail(email);
//        System.out.println("이메일:"+member.getEmail());
//        System.out.println("비밀번호: "+member.getPassword());

        // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 null을 반환합니다.
        if (member == null || !member.getPassword().equals(password)) {
            return false;
        }else{
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

}
