package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static NoTurningBack.jinddobey.service.MemberLoginConst.*;

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
    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public List<Member> blackMemberListS() {
        //블랙멤버 읽어오는 메소드
        return memberRepository.findByState(false);
    }

    @Override
    public int check(String email, String password) {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            return NO_ID;
        }else{
            String dbPassword = member.getPassword();
            if(dbPassword != null) dbPassword = dbPassword.trim();

            if(!dbPassword.equals(password)){
                return NO_PWD;
            }else{
                return YES_ID_PWD;
            }
        }
    }

    @Override
    public Member getLogin(String email) {
        Member member = memberRepository.findByEmail(email);
        return member;
    }
}
