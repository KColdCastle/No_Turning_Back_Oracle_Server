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
    public List<Member> informationAll(String email) {
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

}
